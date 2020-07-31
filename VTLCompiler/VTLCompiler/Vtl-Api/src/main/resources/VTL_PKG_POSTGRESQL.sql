CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_and_bool(p_left character varying, p_right character varying)
 RETURNS character varying
 LANGUAGE plpgsql
 IMMUTABLE STRICT
AS $function$
declare
g_true_value VARCHAR(4) := 'TRUE';
g_false_value VARCHAR(5) := 'FALSE';
v_left_bool BOOLEAN;
v_right_bool BOOLEAN;
v_res_bool BOOLEAN;
v_retval VARCHAR(10);
BEGIN

IF p_left IS NULL 
	THEN
		v_left_bool := null;
	ELSE
		v_left_bool := case when p_left = g_true_value then true else false end;
	END IF;

	IF p_right IS null
		THEN
			v_right_bool := null;
		ELSE
			v_right_bool := case when p_right = g_true_value then true else false end;
		END IF;

        --apply AND operator to boolean values
        v_res_bool := v_left_bool AND v_right_bool;

        IF v_res_bool IS NULL 
        THEN
            v_retval := null;
        ELSE
            v_retval := case when v_res_bool 
           THEN g_true_value else g_false_value end;
        END IF;

        RETURN v_retval;
END;
$function$
;

------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE sv_vtl_sql.vtl_fill_time_series(p_input_dataset character varying, p_id_list character varying, p_id_time character varying, p_limits_method character varying)
 LANGUAGE plpgsql
AS $procedure$
declare

--- Variabili
v_input_dataset  VARCHAR (1000) := p_input_dataset; -- è il nome del dataset di input inviato come parametro nella call della procedura
v_id_list VARCHAR (1000) := p_id_list;  -- è la lista degli identificativi (tranne l'ID di tipo TIME) del dataset di input inviato come parametro nella call della procedura
v_id_time VARCHAR (1000) := p_id_time; -- è l'ID di tipo TIME del dataset di input inviato come parametro nella call della procedura
v_limits_method VARCHAR (1000) := p_limits_method; -- valorizzato con SINGLE o con ALL
        
v_if_exists_cycle_tab int; -- variabile che sarà valorizzata con 0 se non esiste la tabella da droppare, con 1 se esiste. Utilizzo questa variabile per non far andare in errore il DROP nel caso in cui non esiste la tabella FILL_TIME_SERIES_CYCLE
v_if_exists_dsr_tab int;  -- variabile che sarà valorizzata con 0 se non esiste la tabella da droppare, con 1 se esiste. Utilizzo questa variabile per non far andare in errore il DROP nel caso in cui non esiste la tabella FILL_TIME_SERIES_DSR
        
v_min_time VARCHAR(30); --variabile che utilizzerò nel ciclo di incremento dei time
v_max_time VARCHAR(30); --variabile che utilizzerò nel ciclo di incremento dei time
v_loop_time_1 VARCHAR(100); --variabile che utilizzerò nel ciclo di incremento dei time
v_loop_time_2 VARCHAR(100); --variabile che utilizzerò nel ciclo di incremento dei time
v_loop_row int := 1; -- per ciclare le righe della tabella "CURSORE". L'inizializzo a 1
v_loop_row_end int;  -- per concludere il ciclo sulle righe della tabella "CURSORE"
        
--- Scrivo in due variabili i nomi delle tabelle che crea la procedura. Utilizzo le variabili perchè sono più comode negli EXECUTE IMMEDIATE
v_cycle_tab_name VARCHAR (50) :=  'TEMPORARY_FTS_CYCLE';  --- tabella che utilizzo come cursore per ciclare 
v_dsr_tab_name VARCHAR (50) :=  'TEMPORARY_FTS';  --- tabella dove scrivo il DATASET RISULTATO
        
--- Sql statement per costruire il "CURSORE" in caso di v_limits_method='SINGLE'
v_sql_stmt_single VARCHAR (2000) :=
'CREATE TABLE '||v_cycle_tab_name||' AS 
SELECT DISTINCT '
               ||v_id_list||', '||
               'VTL_PERIOD_INDICATOR('||v_id_time||') AS PERIOD, 
               MAX('||v_id_time||') OVER (PARTITION BY '||v_id_list||', VTL_PERIOD_INDICATOR('||v_id_time||')) AS MAX_TIME, 
               MIN('||v_id_time||') OVER (PARTITION BY '||v_id_list||',  VTL_PERIOD_INDICATOR('||v_id_time||')) AS MIN_TIME,
               DENSE_RANK() OVER (ORDER BY '||v_id_list||', VTL_PERIOD_INDICATOR('||v_id_time||')) AS N_ROW
        FROM '||v_input_dataset||
        ' ORDER BY ' ||v_id_list||
                          ' , VTL_PERIOD_INDICATOR('||v_id_time||')';
        
--- Sql statement per costruire il "CURSORE" in caso di v_limits_method='ALL'
v_sql_stmt_all VARCHAR (2000) :=
        'CREATE TABLE '||v_cycle_tab_name||' AS 
        SELECT DISTINCT '
                     ||v_id_list||', '||
                     'VTL_PERIOD_INDICATOR('||v_id_time||') AS PERIOD, 
                     MAX('||v_id_time||') OVER (PARTITION BY VTL_PERIOD_INDICATOR('||v_id_time||')) AS MAX_TIME, 
                     MIN('||v_id_time||') OVER (PARTITION BY  VTL_PERIOD_INDICATOR('||v_id_time||')) AS MIN_TIME,
                     DENSE_RANK() OVER (ORDER BY '||v_id_list||', VTL_PERIOD_INDICATOR('||v_id_time||')) AS N_ROW
        FROM '||v_input_dataset||
        ' ORDER BY ' ||v_id_list||
                          ' , VTL_PERIOD_INDICATOR('||v_id_time||')';
        
--- Sql statement per popolare la variabile v_loop_row_end contando le righe della tabella FILL_TIME_SERIES_CYCLE
v_stmt_loop_row_end  VARCHAR (2000) := 'SELECT COUNT(*) FROM '||v_cycle_tab_name;
        
--- Sql statement per creare la tabella DSR sulla base degli identificativi del DS di input
v_stmt_create_dsr  VARCHAR (2000) := 
        'CREATE TABLE '||v_dsr_tab_name||' AS  
         SELECT DISTINCT '
                     ||v_id_list||', ' 
                     ||v_id_time||' 
         FROM '||v_input_dataset||' 
         WHERE ROWNUM<1';
        
BEGIN -- INIZIO DEL CORPO DELLA PROCEDURA
        
        -- Popolo la variabile v_if_exists_cycle_tab
        EXECUTE IMMEDIATE 'SELECT COUNT(*) FROM ALL_TABLES WHERE TABLE_NAME = :bind_tab' 
        INTO v_if_exists_cycle_tab
        USING v_cycle_tab_name;
        
        -- Popolo la variabile v_if_exists_dsr_tab
        EXECUTE IMMEDIATE 'SELECT COUNT(*) FROM ALL_TABLES WHERE TABLE_NAME = :bind_tab'     
        INTO v_if_exists_dsr_tab
        USING v_dsr_tab_name;
        
        -- se esiste la tabella "CURSORE" effettuo il DROP 
        IF v_if_exists_cycle_tab=1
        THEN
        EXECUTE IMMEDIATE 'DROP TABLE '||v_cycle_tab_name;
        END IF;
        
        -- se esiste la tabella DSR effettuo il DROP 
        IF v_if_exists_dsr_tab=1
        THEN
        EXECUTE IMMEDIATE 'DROP TABLE '||v_dsr_tab_name;
        END IF;
        
        -- CREO la tabella DSR. non avendo informazioni sui datatype creo la tabella a partire dal dataset di input
        EXECUTE IMMEDIATE (v_stmt_create_dsr);
        
        --- creo un cursore differente a seconda del parametro v_limits_method (SINGLE/ALL)
        IF v_limits_method = 'SINGLE'
        THEN
        EXECUTE IMMEDIATE(v_sql_stmt_single);
        ELSE --- posso utilizzare l'ELSE perchè il parametro ha superato i controlli semantici ed è valorizzato sicuramente bene
        EXECUTE IMMEDIATE(v_sql_stmt_all);
        END IF;
        
        --- popolo la variabile v_loop_row_end contando le righe della tabella FILL_TIME_SERIES_CYCLE. La variabile mi fornirà la condizione di uscita dal ciclo sul "CURSORE"
        EXECUTE IMMEDIATE (v_stmt_loop_row_end)
        INTO v_loop_row_end;
        
        -- faccio ciclare le righe del "CURSORE"   
        WHILE v_loop_row <= v_loop_row_end
        LOOP
            EXECUTE IMMEDIATE 'SELECT MIN_TIME FROM '||v_cycle_tab_name||' WHERE N_ROW = :bind_looprow'
            INTO v_min_time
            USING v_loop_row;
            
            EXECUTE IMMEDIATE 'SELECT MAX_TIME FROM '||v_cycle_tab_name||' WHERE N_ROW = :bind_looprow'   
            INTO v_max_time 
            USING v_loop_row;
        
            v_loop_time_1 := TIMESHIFT(v_min_time, -1); 
            v_loop_time_2 := TIMESHIFT(v_min_time, -1);
         
            WHILE v_loop_time_2 <> v_max_time 
            LOOP
            
            v_loop_time_2 := TIMESHIFT(v_loop_time_1, 1);
            
            EXECUTE IMMEDIATE  'INSERT INTO '||v_dsr_tab_name||' 
                                                  SELECT '||v_id_list||', '||''''||v_loop_time_2||''''||'  
                                                  FROM '||v_cycle_tab_name||' 
                                                  WHERE N_ROW = :bind_loop' 
            USING v_loop_row;
            COMMIT;
            
            v_loop_time_1 := v_loop_time_2;
                                                  
            END LOOP;
        
            v_loop_row := v_loop_row + 1;                
        END LOOP;
        
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                NULL;
            WHEN OTHERS THEN
                -- Consider logging the error and then re-raise
                RAISE;
               
               
END;
$procedure$
;

------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_is_number(p_string character varying)
 RETURNS boolean
 LANGUAGE plpgsql
 IMMUTABLE STRICT
AS $function$
DECLARE x NUMERIC;
BEGIN
    x = $1::NUMERIC;
    RETURN TRUE;
EXCEPTION WHEN others THEN
    RETURN FALSE;
END;
$function$
;

------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_iso_week_to_date(p_iso_year integer, p_iso_week integer)
 RETURNS date
 LANGUAGE plpgsql
 IMMUTABLE STRICT
AS $function$
declare
v_jan4_of_iso_year DATE;
v_first_day_of_iso_year DATE;
v_iso_date DATE;
v_iso_date_iso_year INTEGER;
datepart INTEGER;
BEGIN
	v_jan4_of_iso_year := TO_DATE(p_iso_year || '-01-01', 'YYYY-MM-DD');
    datepart := extract(dow from v_jan4_of_iso_year) ;
    IF datepart <= 4 THEN
   		v_first_day_of_iso_year := v_jan4_of_iso_year + (1 - datepart);
   	ELSE
   		v_first_day_of_iso_year := v_jan4_of_iso_year + (8 - datepart);
   	END IF;
    
    v_iso_date := v_first_day_of_iso_year + 7 * (p_iso_week - 1);

    --v_iso_date_iso_year := TO_CHAR(v_iso_date, 'IYYY');
   
   	--IF v_iso_date_iso_year <> p_iso_year THEN
   	--RETURN null;
	--ELSE
    RETURN v_iso_date;
   	--END IF; 
END;
$function$
;

------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_not_bool(p_param character varying)
 RETURNS character varying
 LANGUAGE plpgsql
 IMMUTABLE STRICT
AS $function$
declare
g_true_value VARCHAR(4) := 'TRUE';
g_false_value VARCHAR(5) := 'FALSE';
v_retval VARCHAR(10);
BEGIN
	IF p_param IS NULL 
		THEN
			v_retval := null;
	ELSE
        IF p_param = g_true_value 
        	THEN
                v_retval := g_false_value;
            ELSE
                v_retval := g_true_value;
        END IF;
	END IF;

    RETURN v_retval;
END;
$function$
;

------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_or_bool(p_left character varying, p_right character varying)
 RETURNS character varying
 LANGUAGE plpgsql
 IMMUTABLE STRICT
AS $function$
declare
g_true_value VARCHAR(4) := 'TRUE';
g_false_value VARCHAR(5) := 'FALSE';
v_left_bool BOOLEAN;
v_right_bool BOOLEAN;
v_res_bool BOOLEAN;
v_retval VARCHAR(10);
BEGIN

IF p_left IS NULL 
	THEN
		v_left_bool := null;
	ELSE
		v_left_bool := case when p_left = g_true_value then true else false end;
	END IF;

	IF p_right IS null
		THEN
			v_right_bool := null;
		ELSE
			v_right_bool := case when p_right = g_true_value then true else false end;
		END IF;

        --apply AND operator to boolean values
        v_res_bool := v_left_bool OR v_right_bool;

        IF v_res_bool IS NULL 
        THEN
            v_retval := null;
        ELSE
            v_retval := case when v_res_bool 
           THEN g_true_value else g_false_value end;
        END IF;

        RETURN v_retval;
END;
$function$
;

------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_period_indicator(p_timeval character varying)
 RETURNS character varying
 LANGUAGE plpgsql
 IMMUTABLE STRICT
AS $function$
declare
g_time_separator VARCHAR(1) := '/';
g_period_day VARCHAR(1) := 'D';
g_period_week VARCHAR(1) := 'W';
g_period_month VARCHAR(1) := 'M';
g_period_quarter VARCHAR(1) := 'Q';
g_period_semester VARCHAR(1) := 'S';
g_period_year VARCHAR(1) := 'A';
g_period_date_time VARCHAR(1) := 'T';

v_size INTEGER;
v_period VARCHAR(1);
v_retval VARCHAR(50);
BEGIN
--SDMX v2.1 Time Period format
--yyyy-Qq (2014-Q1, 2014-Q2, 2014-Q3, 2014-Q4)
--yyyy-mm (2014-01, 2014-02, 2014-03, 2014-04, 2014-05, 2014-06, 2014-07, 2014-08, 2014-09, 2014-10, 2014-11, 2014-12)
--yyyy-Mmm (2014-M01,2014-M02,2014-M03, 2014-M04,2014-M05,2014-M06)
--other supported format
--like SDMX without '-'

v_size := LENGTH(p_timeval);

CASE v_size
	WHEN 4 THEN
    --date YYYY format
    	v_retval := g_period_year;
    WHEN 10 THEN
    	v_retval := g_period_date_time;
    ELSE 
        IF v_size < 5 THEN
        	v_retval := 'Invalid format';
        ELSE
            v_period := SUBSTR(p_timeval, 5, 1);

        IF v_period = '-' THEN
        --SDMX v2.1 Time Period format
        	v_period := SUBSTR(p_timeval, 6, 1);

            IF sv_vtl_sql.vtl_is_number(v_period) THEN
            	v_retval := g_period_month; 
            ELSE
            	IF v_period = g_period_day or v_period = g_period_week 
                	OR v_period = g_period_month or v_period = g_period_quarter 
                    OR v_period = g_period_semester or v_period = g_period_year THEN
                            v_retval := v_period; 
                ELSE
                	v_retval := 'Invalid format';
                END IF;
            END IF;
		ELSE
        --other supported format
        	IF sv_vtl_sql.vtl_is_number(v_period) THEN
            	v_retval := g_period_month; 
            ELSE
            	IF v_period = g_period_day or v_period = g_period_week 
                	OR v_period = g_period_month or v_period = g_period_quarter 
                    OR v_period = g_period_semester or v_period = g_period_year THEN
                            v_retval := v_period; 
                ELSE
                	v_retval := 'Invalid format';
                END IF;
            END IF;
		END IF;
	END IF;    
END CASE;

RETURN v_retval;
END;
$function$
;


------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_timeshift(p_timeval character varying, p_shiftval integer)
 RETURNS character varying
 LANGUAGE plpgsql
 IMMUTABLE STRICT
AS $function$
declare
g_time_separator VARCHAR(1) := '/';
g_period_day VARCHAR(1) := 'D';
g_period_week VARCHAR(1) := 'W';
g_period_month VARCHAR(1) := 'M';
g_period_quarter VARCHAR(1) := 'Q';
g_period_semester VARCHAR(1) := 'S';
g_period_year VARCHAR(1) := 'A';

v_size INTEGER;
v_position INTEGER;
v_period VARCHAR(1);
v_leftval VARCHAR(50);
v_rightval VARCHAR(50);
v_retval VARCHAR;
BEGIN
	v_size := LENGTH(p_timeval);

        CASE v_size
          WHEN 4 THEN
            --date YYYY format
            v_retval := p_timeval::INTEGER + p_shiftval;
          WHEN 10 THEN
            --date YYYY-MM-DD format
            v_retval := to_char(sv_vtl_sql.vtl_timeshiftd(to_date(p_timeval, 'YYYY-MM-DD'), p_shiftval), 'YYYY-MM-DD');
          ELSE 
            IF v_size < 5 THEN
                v_retval := 'Invalid format';
            ELSE
                v_position := strpos(p_timeval, g_time_separator); --INSTR
                IF v_position <> 0 THEN
                    --In this case the increase is annual and not monthly
                    v_leftval := sv_vtl_sql.vtl_timeshift(SUBSTR(p_timeval, 1, v_position - 1), p_shiftval * 12);
                    v_rightval := sv_vtl_sql.vtl_timeshift(SUBSTR(p_timeval, v_position + 1), p_shiftval * 12);
                    v_retval := v_leftval || g_time_separator || v_rightval;
                ELSE
                    v_period := SUBSTR(p_timeval, 5, 1);

                    IF v_period = '-' THEN
                        v_period := SUBSTR(p_timeval, 6, 1);

                        CASE v_period
                            WHEN g_period_day THEN
                                --date YYYY-Dddd format OK
                                v_retval := to_char(to_date(SUBSTR(p_timeval, 1, 4) || SUBSTR(p_timeval, 7, 3), 'YYYYDDD') + p_shiftval, 'YYYYDDD');
                                v_retval := SUBSTR(v_retval, 1, 4) || '-' || g_period_day || SUBSTR(v_retval, 5, 3);
                            WHEN g_period_week THEN
                                --date YYYY-Www format OK
                                v_retval := to_char(sv_vtl_sql.vtl_iso_week_to_date(SUBSTR(p_timeval, 1, 4)::INTEGER, SUBSTR(p_timeval, 7, 2)::INTEGER) + (p_shiftval * 7), 'YYYYWW');
                                v_retval := SUBSTR(v_retval, 1, 4) || '-' || g_period_week || SUBSTR(v_retval, 5, 2);
                            WHEN g_period_month THEN
                                --period YYYY-Mmm format OK
                                v_retval := to_char(to_date(SUBSTR(p_timeval, 1, 4) || SUBSTR(p_timeval, 7, 2), 'YYYYMM') + p_shiftval * interval '1 month', 'YYYYMM');
                                v_retval := SUBSTR(v_retval, 1, 4) || '-' || g_period_month || SUBSTR(v_retval, 5, 2);
                            WHEN g_period_quarter THEN
                                --date YYYY-Qq format OK
                                v_retval := to_char(to_date(SUBSTR(p_timeval, 1, 4) || SUBSTR(p_timeval, 7, 1)::INTEGER*3, 'YYYYMM') + p_shiftval * interval '3 months', 'YYYYMM');
                                v_retval := SUBSTR(v_retval, 1, 4) || '-' || g_period_quarter || SUBSTR(v_retval, 5, 2)::INTEGER/3;
                            WHEN g_period_semester THEN
                                --date YYYY-Ss format OK
                                v_retval := to_char(to_date(SUBSTR(p_timeval, 1, 4) || SUBSTR(p_timeval, 7, 1)::INTEGER*6, 'YYYYMM') + p_shiftval * interval '6 months', 'YYYYMM');
                                v_retval := SUBSTR(v_retval, 1, 4) || '-' || g_period_semester || SUBSTR(v_retval, 5, 2)::INTEGER/6; 
                            WHEN g_period_year THEN
                                --period YYYY-A format OK
                                v_retval := SUBSTR(p_timeval, 1, 4)::INTEGER + p_shiftval || '-' || g_period_year;
                            ELSE 
                                IF v_size = 7 THEN
                                    --date YYYY-mm format OK
                                    v_retval := to_char(to_date(p_timeval, 'YYYY-MM') + p_shiftval * interval '1 month', 'YYYY-MM');
                                ELSE
                                    IF v_size = 8 THEN
                                        --date YYYY-xMM format OK
                                        v_retval := to_char(to_date(SUBSTR(p_timeval, 1, 4) || SUBSTR(p_timeval, 7, 2), 'YYYYMM') + p_shiftval * interval '1 month', 'YYYYMM');
                                        v_retval := SUBSTR(v_retval, 1, 4) || SUBSTR(p_timeval, 5, 2) || SUBSTR(v_retval, 5, 2); 
                                    ELSE
                                        v_retval := 'Invalid format';
                                    END IF; 
                                END IF;    
                        END CASE;
                    ELSE
                        CASE v_period
                            WHEN g_period_day THEN
                                --date YYYYDddd format OK
                                v_retval := to_char(to_date(SUBSTR(p_timeval, 1, 4) || SUBSTR(p_timeval, 6, 3), 'YYYYDDD') + p_shiftval, 'YYYYDDD');
                                v_retval := SUBSTR(v_retval, 1, 4) || g_period_day || SUBSTR(v_retval, 5, 3);
                            WHEN g_period_week THEN
                                --date YYYYWww format MANCA FUNZIONE
                                v_retval := to_char(sv_vtl_sql.vtl_iso_week_to_date(SUBSTR(p_timeval, 1, 4)::INTEGER, SUBSTR(p_timeval, 6, 2)::INTEGER) + (p_shiftval * 7), 'YYYYWW');
                                v_retval := SUBSTR(v_retval, 1, 4) || g_period_week || SUBSTR(v_retval, 5, 2);
                            WHEN g_period_month THEN
                                --period YYYYMmm format OK
                                v_retval := to_char(to_date(SUBSTR(p_timeval, 1, 4) || SUBSTR(p_timeval, 6, 2), 'YYYYMM') + p_shiftval * interval '1 month', 'YYYYMM');
                                v_retval := SUBSTR(v_retval, 1, 4) || g_period_month || SUBSTR(v_retval, 5, 2);
                            WHEN g_period_quarter THEN
                                --date YYYYQq format OK
                                v_retval := to_char(to_date(SUBSTR(p_timeval, 1, 4) || SUBSTR(p_timeval, 6, 1)::INTEGER*3, 'YYYYMM') + p_shiftval * interval '3 months', 'YYYYMM');
                                v_retval := SUBSTR(v_retval, 1, 4) || g_period_quarter || SUBSTR(v_retval, 5, 2)::INTEGER/3;
                            WHEN g_period_semester THEN
                                --date YYYYSs format OK
                                v_retval := to_char(to_date(SUBSTR(p_timeval, 1, 4) || SUBSTR(p_timeval, 6, 1)::INTEGER*6, 'YYYYMM') + p_shiftval * interval '6 months', 'YYYYMM');
                                v_retval := SUBSTR(v_retval, 1, 4) || g_period_semester || SUBSTR(v_retval, 5, 2)::INTEGER/6; 
                            WHEN g_period_year THEN
                                --period YYYYA format OK
                                v_retval := SUBSTR(p_timeval, 1, 4)::INTEGER + p_shiftval || g_period_year;
                            ELSE 
                                IF v_size = 6 THEN
                                    --date YYYYMM format OK
                                    v_retval := to_char(to_date(p_timeval, 'YYYYMM') + p_shiftval * interval '1 month', 'YYYYMM');
                                ELSE
                                    IF v_size = 7 THEN
                                        --date YYYYxMM format OK
                                        v_retval := to_char(to_date(SUBSTR(p_timeval, 1, 4) || SUBSTR(p_timeval, 6, 2), 'YYYYMM') + p_shiftval * interval '1 month', 'YYYYMM');
                                        v_retval := SUBSTR(v_retval, 1, 4) || SUBSTR(p_timeval, 5, 1) || SUBSTR(v_retval, 5, 2); 
                                    ELSE
                                        v_retval := 'Invalid format';
                                    END IF; 
                                END IF;    
                        END CASE;
                    END IF;   
                END IF;    
            END IF;
        END CASE;

        RETURN v_retval;
END;
$function$
;

------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_timeshiftd(p_timeval date, p_shiftval integer)
 RETURNS date
 LANGUAGE plpgsql
 IMMUTABLE STRICT
AS $function$
declare
v_retval DATE;
BEGIN
	v_retval := p_timeval + p_shiftval * interval '1 year';

    RETURN v_retval;
END;
$function$
;

------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_timeshiftt(p_timeval timestamp without time zone, p_shiftval integer)
 RETURNS date
 LANGUAGE plpgsql
 IMMUTABLE STRICT
AS $function$
declare
v_retval TIMESTAMP;
BEGIN
	v_retval := p_timeval + p_shiftval * interval '1 year';

    RETURN v_retval;
END;
$function$
;

------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_xor_bool(p_left character varying, p_right character varying)
 RETURNS character varying
 LANGUAGE plpgsql
 IMMUTABLE STRICT
AS $function$
declare
g_true_value VARCHAR(4) := 'TRUE';
g_false_value VARCHAR(5) := 'FALSE';
v_left_bool BOOLEAN;
v_right_bool BOOLEAN;
v_res_bool BOOLEAN;
v_retval VARCHAR(10);
BEGIN
	IF p_left IS NULL OR p_right IS NULL 
		THEN
			v_retval := null;
		ELSE
        IF p_left = p_right 
        	THEN
                v_retval := g_false_value;
            ELSE
                v_retval := g_true_value;
        END IF;
	END IF;

    RETURN v_retval;
END;
$function$
;

------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_time_agg(p_timeval timestamp without time zone, p_period_to character varying, p_period_from character varying, p_start character varying)
 RETURNS timestamp without time zone
 LANGUAGE plpgsql
 IMMUTABLE
AS $function$
declare
v_retval timestamp;
       
BEGIN
	v_retval := to_date(sv_vtl_sql.vtl_time_agg(to_char(p_timeval, 'YYYY-MM-DD'), p_period_to, p_period_from, p_start), 'YYYY-MM-DD');
	RETURN v_retval;
END;
$function$
;

------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_time_agg(p_timeval character varying, p_period_to character varying, p_period_from character varying, p_start character varying)
 RETURNS character varying
 LANGUAGE plpgsql
 IMMUTABLE
AS $function$
declare
g_true_value VARCHAR(4) := 'TRUE';
g_false_value VARCHAR(5) := 'FALSE';
g_time_separator VARCHAR(1) := '/';
g_period_day VARCHAR(1) := 'D';
g_period_week VARCHAR(1) := 'W';
g_period_month VARCHAR(1) := 'M';
g_period_quarter VARCHAR(1) := 'Q';
g_period_semester VARCHAR(1) := 'S';
g_period_year VARCHAR(1) := 'A';
   
v_size INTEGER;
v_position INTEGER;
v_period VARCHAR(1);
v_start VARCHAR(10);
v_retval VARCHAR(50);
       
BEGIN

IF p_timeval = 'NULL' THEN
	return null;
END IF; 
        
v_start := coalesce(p_start, 'first');
        
v_size := LENGTH(p_timeval);

CASE v_size
	WHEN 4 THEN
    --date YYYY format
    	v_retval := p_timeval;
	WHEN 10 THEN
    --date YYYY-MM-DD format
    	CASE p_period_to
        	WHEN g_period_day THEN
            	v_retval := p_timeval;
        	WHEN g_period_week THEN
            	IF v_start = 'first' THEN
                	v_retval := to_char(date_trunc('W', p_timeval::date), 'YYYY-MM-DD');
                ELSE
                	v_retval := to_char(date_trunc('W', p_timeval::date) + interval '6 day', 'YYYY-MM-DD');
                END IF;
        	WHEN g_period_month THEN
            	IF v_start = 'first' THEN
                	v_retval := to_char(date_trunc('month', p_timeval::date), 'YYYY-MM-DD');
                ELSE
                	v_retval := to_char((date_trunc('month', p_timeval::date) + interval '1 month' - interval '1 day')::date, 'YYYY-MM-DD');
                END IF;
            WHEN g_period_quarter THEN
            	IF SUBSTR(p_timeval, 6, 2)::INTEGER <= 3 THEN
                	IF v_start = 'first' THEN
                    	v_retval := SUBSTR(p_timeval, 1, 4) || '-01-01';
                	ELSE
                    	v_retval := SUBSTR(p_timeval, 1, 4) || '-03-31';
                    END IF;
            	ELSIF SUBSTR(p_timeval, 6, 2)::INTEGER <= 6 THEN
                	IF v_start = 'first' THEN
                    	v_retval := SUBSTR(p_timeval, 1, 4) || '-04-01';
                	ELSE
                    	v_retval := SUBSTR(p_timeval, 1, 4) || '-06-30';
                    END IF;
            	ELSIF SUBSTR(p_timeval, 6, 2)::INTEGER <= 9 THEN
                	IF v_start = 'first' THEN
                    	v_retval := SUBSTR(p_timeval, 1, 4) || '-07-01';
                	ELSE
                    	v_retval := SUBSTR(p_timeval, 1, 4) || '-09-30';
                    END IF;
            	ELSE
                	IF v_start = 'first' THEN
                    	v_retval := SUBSTR(p_timeval, 1, 4) || '-10-01';
                	ELSE
                    	v_retval := SUBSTR(p_timeval, 1, 4) || '-12-31';
                    END IF;
                END IF; 
			WHEN g_period_semester THEN
            	IF SUBSTR(p_timeval, 6, 2)::INTEGER <= 6 THEN
                	IF v_start = 'first' THEN
                    	v_retval := SUBSTR(p_timeval, 1, 4) || '-01-01';
                	ELSE
                    	v_retval := SUBSTR(p_timeval, 1, 4) || '-06-30';
                    END IF;
            	ELSE
                	IF v_start = 'first' THEN
                    	v_retval := SUBSTR(p_timeval, 1, 4) || '-07-01';
                    ELSE
                    	v_retval := SUBSTR(p_timeval, 1, 4) || '-12-31';
                    END IF;
                END IF; 
			WHEN g_period_year THEN
            	IF v_start = 'first' THEN
                	v_retval := SUBSTR(p_timeval, 1, 4) || '-01-01';
                ELSE
                	v_retval := SUBSTR(p_timeval, 1, 4) || '-12-31';
                END IF;
            ELSE 
            	v_retval := p_timeval;
		END CASE;
	ELSE 
		IF v_size < 5 THEN
        	v_retval := 'Invalid format';
    	ELSE
        	v_position := STRPOS(p_timeval, g_time_separator);
            IF v_position <> 0 THEN
            --YYYY-MM/YYYY-MM format
            	v_retval := 'Invalid format';
        	ELSE
            	v_period := SUBSTR(p_timeval, 5, 1);

				IF v_period = '-' THEN
                	v_period := SUBSTR(p_timeval, 6, 1);

                        CASE v_period
                            WHEN g_period_day THEN
                                --YYYY-Dddd format
                                IF p_period_to = g_period_year THEN
                                    v_retval := SUBSTR(p_timeval, 1, 4);
                                ELSIF p_period_to = g_period_semester THEN
                                    IF SUBSTR(p_timeval, 7, 3) <= to_char(to_date(SUBSTR(p_timeval, 1, 4) || '0630','YYYYMMDD'),'DDD') THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '1';
                                    ELSE
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '2';
                                    END IF;
                                ELSIF p_period_to = g_period_quarter THEN
                                    IF SUBSTR(p_timeval, 7, 3) <= to_char(to_date(SUBSTR(p_timeval, 1, 4) || '0331','YYYYMMDD'),'DDD') THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '1';
                                    ELSIF SUBSTR(p_timeval, 7, 3) <= to_char(to_date(SUBSTR(p_timeval, 1, 4) || '0630','YYYYMMDD'),'DDD') THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '2';
                                    ELSIF SUBSTR(p_timeval, 7, 3) <= to_char(to_date(SUBSTR(p_timeval, 1, 4) || '0930','YYYYMMDD'),'DDD') THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '3';
                                    ELSE
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '4';
                                    END IF;
                                ELSIF p_period_to = g_period_month THEN
                                    v_retval := SUBSTR(p_timeval, 1, 4) || g_period_month || to_char(to_date(SUBSTR(p_timeval, 1, 4)|| '0101', 'YYYYMMDD') + (SUBSTR(p_timeval, 7, 3))::INTEGER -1, 'MM');
                                ELSIF p_period_to = g_period_week THEN
                                    v_retval := SUBSTR(p_timeval, 1, 4) || g_period_week || to_char(to_date(SUBSTR(p_timeval, 1, 4)|| '0101', 'YYYYMMDD') + (SUBSTR(p_timeval, 7, 3))::INTEGER -1, 'WW');
                                ELSE
                                    v_retval := p_timeval;
                                END IF;
                            WHEN g_period_week THEN
                                --YYYY-Www format
                                IF p_period_to = g_period_year THEN
                                    v_retval := SUBSTR(p_timeval, 1, 4);
                                ELSIF p_period_to = g_period_semester THEN
                                    IF SUBSTR(p_timeval, 7, 2) <= to_char(to_date(SUBSTR(p_timeval, 1, 4) || '0630','YYYYMMDD'),'WW') THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '1';
                                    ELSE
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '2';
                                    END IF;
                                ELSIF p_period_to = g_period_quarter THEN
                                    IF SUBSTR(p_timeval, 7, 2) <= to_char(to_date(SUBSTR(p_timeval, 1, 4) || '0331','YYYYMMDD'),'WW') THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '1';
                                    ELSIF SUBSTR(p_timeval, 7, 2) <= to_char(to_date(SUBSTR(p_timeval, 1, 4) || '0630','YYYYMMDD'),'WW') THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '2';
                                    ELSIF SUBSTR(p_timeval, 7, 2) <= to_char(to_date(SUBSTR(p_timeval, 1, 4) || '0930','YYYYMMDD'),'WW') THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '3';
                                    ELSE
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '4';
                                    END IF;
                                ELSIF p_period_to = g_period_month THEN
                                    v_retval := SUBSTR(p_timeval, 1, 4) || g_period_month || to_char(vtl_iso_week_to_date(SUBSTR(p_timeval, 1, 4)::INTEGER, SUBSTR(p_timeval, 7, 2)::INTEGER), 'MM');
                                ELSE
                                    v_retval := p_timeval;
                                END IF;
                            WHEN g_period_month THEN
                                --YYYY-Mmm format
                                IF p_period_to = g_period_year THEN
                                    v_retval := SUBSTR(p_timeval, 1, 4);
                                ELSIF p_period_to = g_period_semester THEN
                                    IF SUBSTR(p_timeval, 7, 2)::INTEGER <= 6 THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '1';
                                    ELSE
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '2';
                                    END IF;
                                ELSIF p_period_to = g_period_quarter THEN
                                    IF SUBSTR(p_timeval, 7, 2)::INTEGER <= 3 THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '1';
                                    ELSIF SUBSTR(p_timeval, 7, 2)::INTEGER <= 6 THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '2';
                                    ELSIF SUBSTR(p_timeval, 7, 2)::INTEGER <= 9 THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '3';
                                    ELSE
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '4';
                                    END IF;
                                ELSE
                                    v_retval := p_timeval;
                                END IF; 
                            WHEN g_period_quarter THEN
                                --YYYY-Qq format
                                IF p_period_to = g_period_year THEN
                                    v_retval := SUBSTR(p_timeval, 1, 4);
                                ELSIF p_period_to = g_period_semester THEN
                                    IF SUBSTR(p_timeval, 7, 2)::INTEGER <= 2 THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '1';
                                    ELSE
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '2';
                                    END IF;
                                ELSE
                                    v_retval := p_timeval;
                                END IF; 
                            WHEN g_period_semester THEN
                                --YYYY-Ss format
                                IF p_period_to = g_period_year THEN
                                    --YYYY-xMM format
                                    v_retval := SUBSTR(p_timeval, 1, 4);
                                ELSE
                                    v_retval := p_timeval;
                                END IF; 
                            WHEN g_period_year THEN
                                --YYYY-A format
                                v_retval := SUBSTR(p_timeval, 1, 4);
                            ELSE 
                                IF v_size = 7 THEN
                                    --YYYY-mm format
                                    IF p_period_to = g_period_year THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4);
                                    ELSIF p_period_to = g_period_semester THEN
                                        IF SUBSTR(p_timeval, 6, 2)::INTEGER <= 6 THEN
                                            v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '1';
                                        ELSE
                                            v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '2';
                                        END IF;
                                    ELSIF p_period_to = g_period_quarter THEN
                                        IF SUBSTR(p_timeval, 6, 2)::INTEGER <= 3 THEN
                                            v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '1';
                                        ELSIF SUBSTR(p_timeval, 6, 2)::INTEGER <= 6 THEN
                                            v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '2';
                                        ELSIF SUBSTR(p_timeval, 6, 2)::INTEGER <= 9 THEN
                                            v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '3';
                                        ELSE
                                            v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '4';
                                        END IF;
                                    ELSE
                                        v_retval := p_timeval;
                                    END IF; 
                                ELSE
                                    IF v_size = 8 THEN
                                        --YYYY-xMM format
                                        IF p_period_to = g_period_year THEN
                                            v_retval := SUBSTR(p_timeval, 1, 4);
                                        ELSIF p_period_to = g_period_semester THEN
                                            IF SUBSTR(p_timeval, 7, 2)::INTEGER <= 6 THEN
                                                v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '1';
                                            ELSE
                                                v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '2';
                                            END IF;
                                        ELSIF p_period_to = g_period_quarter THEN
                                            IF SUBSTR(p_timeval, 7, 2)::INTEGER <= 3 THEN
                                                v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '1';
                                            ELSIF SUBSTR(p_timeval, 7, 2)::INTEGER <= 6 THEN
                                                v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '2';
                                            ELSIF SUBSTR(p_timeval, 7, 2)::INTEGER <= 9 THEN
                                                v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '3';
                                            ELSE
                                                v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '4';
                                            END IF;
                                        ELSE
                                            v_retval := p_timeval;
                                        END IF; 
                                    ELSE
                                        v_retval := 'Invalid format';
                                    END IF; 
                                END IF;    
                        END CASE;
				ELSE
                        CASE v_period
                            WHEN g_period_day THEN
                                --YYYYDddd format
                                IF p_period_to = g_period_year THEN
                                    v_retval := SUBSTR(p_timeval, 1, 4);
                                ELSIF p_period_to = g_period_semester THEN
                                    IF SUBSTR(p_timeval, 6, 3) <= to_char(to_date(SUBSTR(p_timeval, 1, 4) || '0630','YYYYMMDD'),'DDD') THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '1';
                                    ELSE
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '2';
                                    END IF;
                                ELSIF p_period_to = g_period_quarter THEN
                                    IF SUBSTR(p_timeval, 6, 3) <= to_char(to_date(SUBSTR(p_timeval, 1, 4) || '0331','YYYYMMDD'),'DDD') THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '1';
                                    ELSIF SUBSTR(p_timeval, 6, 3) <= to_char(to_date(SUBSTR(p_timeval, 1, 4) || '0630','YYYYMMDD'),'DDD') THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '2';
                                    ELSIF SUBSTR(p_timeval, 6, 3) <= to_char(to_date(SUBSTR(p_timeval, 1, 4) || '0930','YYYYMMDD'),'DDD') THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '3';
                                    ELSE
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '4';
                                    END IF;
                                ELSIF p_period_to = g_period_month THEN
                                    v_retval := SUBSTR(p_timeval, 1, 4) || g_period_month || to_char(to_date(SUBSTR(p_timeval, 1, 4)|| '0101', 'YYYYMMDD') + (SUBSTR(p_timeval, 6, 3))::INTEGER -1, 'MM');
                                ELSIF p_period_to = g_period_week THEN
                                    v_retval := SUBSTR(p_timeval, 1, 4) || g_period_week || to_char(to_date(SUBSTR(p_timeval, 1, 4)|| '0101', 'YYYYMMDD') + (SUBSTR(p_timeval, 6, 3))::INTEGER -1, 'WW');
                                ELSE
                                    v_retval := p_timeval;
                                END IF;
                            WHEN g_period_week THEN
                                --YYYYWww format
                                IF p_period_to = g_period_year THEN
                                    v_retval := SUBSTR(p_timeval, 1, 4);
                                ELSIF p_period_to = g_period_semester THEN
                                    IF SUBSTR(p_timeval, 6, 2) <= to_char(to_date(SUBSTR(p_timeval, 1, 4) || '0630','YYYYMMDD'),'WW') THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '1';
                                    ELSE
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '2';
                                    END IF;
                                ELSIF p_period_to = g_period_quarter THEN
                                    IF SUBSTR(p_timeval, 6, 2) <= to_char(to_date(SUBSTR(p_timeval, 1, 4) || '0331','YYYYMMDD'),'WW') THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '1';
                                    ELSIF SUBSTR(p_timeval, 6, 2) <= to_char(to_date(SUBSTR(p_timeval, 1, 4) || '0630','YYYYMMDD'),'WW') THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '2';
                                    ELSIF SUBSTR(p_timeval, 6, 2) <= to_char(to_date(SUBSTR(p_timeval, 1, 4) || '0930','YYYYMMDD'),'WW') THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '3';
                                    ELSE
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '4';
                                    END IF;
                                ELSIF p_period_to = g_period_month THEN
                                    v_retval := SUBSTR(p_timeval, 1, 4) || g_period_month || to_char(vtl_iso_week_to_date(SUBSTR(p_timeval, 1, 4)::INTEGER, SUBSTR(p_timeval, 6, 2)::INTEGER), 'MM');
                                ELSE
                                    v_retval := p_timeval;
                                END IF;
                            WHEN g_period_month THEN
                                --YYYYMmm format
                                IF p_period_to = g_period_year THEN
                                    v_retval := SUBSTR(p_timeval, 1, 4);
                                ELSIF p_period_to = g_period_semester THEN
                                    IF SUBSTR(p_timeval, 6, 2)::INTEGER <= 6 THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '1';
                                    ELSE
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '2';
                                    END IF;
                                ELSIF p_period_to = g_period_quarter THEN
                                    IF SUBSTR(p_timeval, 6, 2)::INTEGER <= 3 THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '1';
                                    ELSIF SUBSTR(p_timeval, 6, 2)::INTEGER <= 6 THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '2';
                                    ELSIF SUBSTR(p_timeval, 6, 2)::INTEGER <= 9 THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '3';
                                    ELSE
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '4';
                                    END IF;
                                ELSE
                                    v_retval := p_timeval;
                                END IF; 
                            WHEN g_period_quarter THEN
                                --YYYYQq format
                                IF p_period_to = g_period_year THEN
                                    v_retval := SUBSTR(p_timeval, 1, 4);
                                ELSIF p_period_to = g_period_semester THEN
                                    IF SUBSTR(p_timeval, 6, 2)::INTEGER <= 2 THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '1';
                                    ELSE
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '2';
                                    END IF;
                                ELSE
                                    v_retval := p_timeval;
                                END IF; 
                            WHEN g_period_semester THEN
                                --YYYYSs format
                                IF p_period_to = g_period_year THEN
                                    --YYYY-xMM format
                                    v_retval := SUBSTR(p_timeval, 1, 4);
                                ELSE
                                    v_retval := p_timeval;
                                END IF; 
                            WHEN g_period_year THEN
                                --YYYYA format
                                v_retval := SUBSTR(p_timeval, 1, 4);
                            ELSE 
                                IF v_size = 6 THEN
                                    --YYYYMM format
                                    IF p_period_to = g_period_year THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4);
                                    ELSIF p_period_to = g_period_semester THEN
                                        IF SUBSTR(p_timeval, 5, 2)::INTEGER <= 6 THEN
                                            v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '1';
                                        ELSE
                                            v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '2';
                                        END IF;
                                    ELSIF p_period_to = g_period_quarter THEN
                                        IF SUBSTR(p_timeval, 5, 2)::INTEGER <= 3 THEN
                                            v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '1';
                                        ELSIF SUBSTR(p_timeval, 5, 2)::INTEGER <= 6 THEN
                                            v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '2';
                                        ELSIF SUBSTR(p_timeval, 5, 2)::INTEGER <= 9 THEN
                                            v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '3';
                                        ELSE
                                            v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '4';
                                        END IF;
                                    ELSE
                                        v_retval := p_timeval;
                                    END IF; 
                                ELSE
                                    IF v_size = 7 THEN
                                        --YYYYxMM format
                                        IF p_period_to = g_period_year THEN
                                            v_retval := SUBSTR(p_timeval, 1, 4);
                                        ELSIF p_period_to = g_period_semester THEN
                                            IF SUBSTR(p_timeval, 6, 2)::INTEGER <= 6 THEN
                                                v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '1';
                                            ELSE
                                                v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '2';
                                            END IF;
                                        ELSIF p_period_to = g_period_quarter THEN
                                            IF SUBSTR(p_timeval, 6, 2)::INTEGER <= 3 THEN
                                                v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '1';
                                            ELSIF SUBSTR(p_timeval, 6, 2)::INTEGER <= 6 THEN
                                                v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '2';
                                            ELSIF SUBSTR(p_timeval, 6, 2)::INTEGER <= 9 THEN
                                                v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '3';
                                            ELSE
                                                v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '4';
                                            END IF;
                                        ELSE
                                            v_retval := p_timeval;
                                        END IF; 
                                    ELSE
                                        v_retval := 'Invalid format';
                                    END IF; 
                                END IF;    
                        END CASE;
                    END IF;   
                END IF;    
	END IF;
END CASE;

RETURN v_retval;
END;
$function$
;

------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_standardize_number(p_value character varying)
 RETURNS numeric
 LANGUAGE plpgsql
 IMMUTABLE STRICT
AS $function$
declare
 
v_standard numeric; 
v_perc BOOLEAN;
v_numeratore numeric;
v_denominatore numeric;
v_power INT;
v_int INT;
        
--- la funzione non ammette che i numeri in formato stringa contengano separatori delle migliaia.
--- è indifferente che il separatore dei decimali sia un punto o una virgola
        
BEGIN
CASE STRPOS(SUBSTR(p_value, 1),'%') 
	WHEN 0 THEN 
		v_perc := false;
	ELSE 
		v_perc := true;
	END CASE; --- mi dice se è una percentuale
v_int := (select (length(REPLACE(REPLACE(p_value, '.','/'),',','/')) - length(a.arr[array_length(a.arr,1)])) as res 
									from (select string_to_array(REPLACE(REPLACE(p_value, '.','/'),',','/'), '/') as arr) as a);
v_numeratore := (REPLACE(REPLACE (REPLACE (p_value, '%', ''), '.', ''),',',''))::numeric;  --tolgo '%' e punti e virgole 
CASE v_perc 
	WHEN TRUE then
		if v_int <> 0 then
    		v_power := LENGTH(p_value) - v_int - 1;
    	else
    		v_power := 0;
    	end if;
    ELSE 
    	if v_int <> 0 then
    		v_power := LENGTH(p_value) - v_int;
    	else
    		v_power := 0;
    	end if;
END CASE; 
v_denominatore := POWER(10,v_power); 
CASE v_perc 
	WHEN TRUE THEN 
		v_standard := v_numeratore/(100*v_denominatore);
    ELSE 
    	v_standard := v_numeratore / (v_denominatore);
END CASE;
          
RETURN v_standard ;
EXCEPTION
        WHEN NO_DATA_FOUND
           THEN
              NULL;
        WHEN OTHERS
           THEN
              -- Consider logging the error and then re-raise
              RETURN NULL ;--RAISE;
   
END;
$function$
;

------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_cast_to_int(p_value character varying, p_datatype character varying)
 RETURNS integer
 LANGUAGE plpgsql
 IMMUTABLE STRICT
AS $function$
declare
v_output_value INTEGER; 
g_true_value VARCHAR(4) := 'TRUE';
g_false_value VARCHAR(5) := 'FALSE';
begin
	case p_datatype 
            when 'STRING' then v_output_value :=  CAST(CAST(REPLACE(p_value,',','.') AS NUMERIC) AS INTEGER);
            when 'NUMBER' then v_output_value :=  vtl_standardize_number(p_value); 
            when 'BOOLEAN' then
                case 
                    when UPPER(p_value) IN ( g_true_value, '1') 
                        then v_output_value := 1; 
                        else v_output_value := 0; 
                end case;
        end case; 
  
    return v_output_value;
END;
$function$
;

------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_cast_to_number(p_value character varying, p_datatype character varying, p_mask character varying)
 RETURNS numeric
 LANGUAGE plpgsql
 IMMUTABLE STRICT
AS $function$
declare
v_output_value numeric; 
g_true_value VARCHAR(4) := 'TRUE';
g_false_value VARCHAR(5) := 'FALSE';
begin
	case p_datatype 
            when 'BOOLEAN' then 
                case 
                    when UPPER(p_value) IN (g_true_value, '1') 
                        then v_output_value := 1; 
                        else v_output_value := 0; 
                end case;
            when 'STRING' then 
			case
				when cast(replace(p_value,',','.') as numeric) > cast(replace(replace(p_mask,'D','9'),',','.') as numeric) then
			    	v_output_value := p_mask;
				else
					v_output_value := cast(replace(p_value,',','.') as numeric);
			end case;
            when 'INTEGER' then v_output_value := sv_vtl_sql.vtl_standardize_number(p_value);
        end case;
  
    return v_output_value;
END;
$function$
;

------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_cast_to_boolean(p_value character varying, p_datatype character varying)
 RETURNS boolean
 LANGUAGE plpgsql
 IMMUTABLE STRICT
AS $function$
declare
g_true_value VARCHAR(4) := 'TRUE';
g_false_value VARCHAR(5) := 'FALSE';
v_output_value BOOLEAN;

begin
	case p_datatype 
            when 'INTEGER' then 
                case 
                    when p_value <> '0' then v_output_value := TRUE;
                    else v_output_value := FALSE;
                end case;
            when 'NUMBER' then 
                case 
                    when p_value <> '0' then v_output_value := TRUE;
                    else v_output_value := FALSE;
                end case;
            /*when 'STRING' then 
                case 
                    when p_value = g_true_value then v_output_value := TRUE;
                    when p_value = g_false_value then v_output_value := FALSE;
                end case;*/            
        end case; 
  
        return v_output_value;
END;
$function$
;


------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_cast_to_time(p_value character varying, p_datatype character varying, p_mask character varying)
 RETURNS character varying
 LANGUAGE plpgsql
 IMMUTABLE
AS $function$
declare
g_true_value VARCHAR(4) := 'TRUE';
g_false_value VARCHAR(5) := 'FALSE';
g_time_separator VARCHAR(1) := '/';
g_period_day VARCHAR(1) := 'D';
g_period_week VARCHAR(1) := 'W';
g_period_month VARCHAR(1) := 'M';
g_period_quarter VARCHAR(1) := 'Q';
g_period_semester VARCHAR(1) := 'S';
g_period_year VARCHAR(1) := 'A';
v_period VARCHAR(1);
v_year VARCHAR(4);
v_first_char INTEGER;
v_output_value character varying; 
v_position INTEGER;
v_left_mask VARCHAR(50);
v_right_mask VARCHAR(50);
v_left_value VARCHAR(50);
v_righ_value VARCHAR(50);

begin
		case p_datatype 
            when 'STRING' then 
				IF(LENGTH(p_value) <> LENGTH(p_mask))
				THEN
					RAISE EXCEPTION 'Invalid cast.';
				END IF;
				
                v_position := strpos(p_mask, g_time_separator);
                IF v_position <> 0 THEN
                    v_left_mask := TRIM(SUBSTR(p_mask, 1, v_position - 1));
                    v_right_mask := TRIM(SUBSTR(p_mask, v_position + 1));

                    v_position := strpos(p_value, g_time_separator);

                    v_left_value := TRIM(SUBSTR(p_value, 1, v_position - 1));
                    v_righ_value := TRIM(SUBSTR(p_value, v_position + 1));

                    v_output_value := to_char(TO_TIMESTAMP(v_left_value, v_left_mask), v_left_mask) || g_time_separator || to_char(TO_TIMESTAMP(v_righ_value, v_right_mask), v_right_mask);
                ELSE
					RAISE EXCEPTION 'Invalid cast.';
                END IF;
             
           	when 'DATE' then v_output_value := to_char(to_date(p_value, 'YYYY-MM-DD'), 'YYYY-MM-DD') || '/' || to_char(to_date(p_value, 'YYYY-MM-DD'), 'YYYY-MM-DD');
            when 'TIME_PERIOD' then               
                v_year := SUBSTR(p_value, 1, 4);
                
                v_first_char := 5;
                v_period := SUBSTR(p_value, v_first_char, 1);

                IF v_period = '-' THEN
                    v_first_char := 6;
                    v_period := SUBSTR(p_value, v_first_char, 1);
                END IF;
                
                v_first_char := v_first_char +1;
                
                CASE v_period
                    WHEN g_period_day THEN
                        v_output_value := to_char(to_date(v_year || SUBSTR(p_value, v_first_char, 3), 'YYYYDDD'), 'YYYY-MM-DD') || '/' || to_char(to_date(v_year || SUBSTR(p_value, v_first_char, 3), 'YYYYDDD'), 'YYYY-MM-DD');
                    WHEN g_period_week THEN
                        --v_output_value := to_char(date_trunc('IW', to_date(v_year || to_char(SUBSTR(p_value, v_first_char, 2)::INTEGER * 7, '000'), 'YYYYDDD')), 'YYYY-MM-DD') || '/' || to_char(date_trunc('IW', to_date(v_year || to_char(SUBSTR(p_value, v_first_char, 2)::INTEGER * 7, '000'), 'YYYYDDD')) +6, 'YYYY-MM-DD');
                        v_output_value := vtl_iso_week_to_date(v_year::INTEGER, SUBSTR(p_value, v_first_char, 2)::INTEGER) || '/' || vtl_iso_week_to_date(v_year::INTEGER, SUBSTR(p_value, v_first_char, 2)::INTEGER) + interval '6 day';
                    WHEN g_period_month THEN
                        v_output_value := to_date(v_year || SUBSTR(p_value, v_first_char, 2) || '01', 'YYYYMMDD') || '/' || to_char((date_trunc('month', (v_year || SUBSTR(p_value, v_first_char, 2) || '01')::date) + interval '1 month' - interval '1 day')::date, 'YYYY-MM-DD');--last_day(to_date(v_year || SUBSTR(p_value, v_first_char, 2) || '01', 'YYYYMMDD'));
                    WHEN g_period_quarter THEN
                        IF SUBSTR(p_value, v_first_char, 1) = '1' THEN
                            v_output_value := to_date(v_year || '0101', 'YYYYMMDD') || '/' || to_date(v_year || '0331', 'YYYYMMDD');
                        ELSIF SUBSTR(p_value, v_first_char, 1) = '2' THEN
                            v_output_value := to_date(v_year || '0401', 'YYYYMMDD') || '/' || to_date(v_year || '0630', 'YYYYMMDD');
                        ELSIF SUBSTR(p_value, v_first_char, 1) = '3' THEN
                            v_output_value := to_date(v_year || '0701', 'YYYYMMDD') || '/' || to_date(v_year || '0930', 'YYYYMMDD');
                        ELSE
                            v_output_value := to_date(v_year || '1001', 'YYYYMMDD') || '/' || to_date(v_year || '1231', 'YYYYMMDD');
                        END IF;             
                    WHEN g_period_semester THEN
                        IF SUBSTR(p_value, v_first_char, 1) = '1' THEN
                            v_output_value := to_date(v_year || '0101', 'YYYYMMDD') || '/' || to_date(v_year || '0630', 'YYYYMMDD');
                        ELSE
                            v_output_value := to_date(v_year || '0701', 'YYYYMMDD') || '/' || to_date(v_year || '1231', 'YYYYMMDD');
                        END IF;
                    WHEN g_period_year THEN
                            v_output_value := to_date(v_year || '0101', 'YYYYMMDD') || '/' || to_date(v_year || '1231', 'YYYYMMDD');
                END CASE;                
        end case; 
      
        return v_output_value;
END;
$function$
;

------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_cast_to_time(p_value date, p_datatype character varying, p_mask character varying)
 RETURNS character varying
 LANGUAGE plpgsql
 STRICT
AS $function$declare
v_output_value character varying;

begin
	case p_datatype 
    	when 'DATE' then v_output_value := to_char(p_value, 'YYYY-MM-DD') || '/' || to_char(p_value, 'YYYY-MM-DD');
    end case; 
  
    return v_output_value;
END;$function$
;


------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_cast_to_date(p_value character varying, p_datatype character varying, p_mask character varying)
 RETURNS date
 LANGUAGE plpgsql
 IMMUTABLE
AS $function$
declare
v_start VARCHAR(10);
v_period VARCHAR(1);
v_year VARCHAR(4);
v_first_char INTEGER;
v_output_value DATE;
g_true_value VARCHAR(4) := 'TRUE';
g_false_value VARCHAR(5) := 'FALSE';
g_time_separator VARCHAR(1) := '/';
g_period_day VARCHAR(1) := 'D';
g_period_week VARCHAR(1) := 'W';
g_period_month VARCHAR(1) := 'M';
g_period_quarter VARCHAR(1) := 'Q';
g_period_semester VARCHAR(1) := 'S';
g_period_year VARCHAR(1) := 'A';

begin
case p_datatype 
            when 'STRING' then
				IF(LENGTH(p_value) <> LENGTH(p_mask))
				THEN
					RAISE EXCEPTION 'Invalid cast.';
				END IF;
				v_output_value := TO_DATE ( p_value, p_mask); 
            when 'TIME_PERIOD' then 
                v_start := coalesce(p_mask, 'START');
                v_year := SUBSTR(p_value, 1, 4);
                
                v_first_char := 5;
                v_period := SUBSTR(p_value, v_first_char, 1);

                IF v_period = '-' THEN
                    v_first_char := 6;
                    v_period := SUBSTR(p_value, v_first_char, 1);
                END IF;
                
                v_first_char := v_first_char +1;
                
                CASE v_period
                    WHEN g_period_day THEN
                        v_output_value := to_date(v_year || SUBSTR(p_value, v_first_char, 3), 'YYYYDDD');
                    WHEN g_period_week THEN
                        IF v_start = 'START' THEN
                            --v_output_value := date_trunc('W', to_date(v_year || to_char(SUBSTR(p_value, v_first_char, 2)::INTEGER * 7, '000'), 'YYYYDDD'));
                            v_output_value := vtl_iso_week_to_date(v_year::INTEGER, SUBSTR(p_value, v_first_char, 2)::INTEGER);
                        ELSE
                            --v_output_value := date_trunc('W', to_date(v_year || to_char(SUBSTR(p_value, v_first_char, 2)::INTEGER * 7, '000'), 'YYYYDDD')) + interval '6 day';
                       		v_output_value := vtl_iso_week_to_date(v_year::INTEGER, SUBSTR(p_value, v_first_char, 2)::INTEGER) + interval '6 day'; 
                       END IF;
                    WHEN g_period_month THEN
                        IF v_start = 'START' THEN
                            v_output_value := to_date(v_year || SUBSTR(p_value, v_first_char, 2) || '01', 'YYYYMMDD');
                        ELSE
                            v_output_value := to_char((date_trunc('month', to_date(v_year || SUBSTR(p_value, v_first_char, 2) || '01', 'YYYYMMDD')) + interval '1 month' - interval '1 day')::date, 'YYYY-MM-DD');
                        END IF;
                    WHEN g_period_quarter THEN
                        IF SUBSTR(p_value, v_first_char, 1) = '1' THEN
                            IF v_start = 'START' THEN
                                v_output_value := to_date(v_year || '0101', 'YYYYMMDD');
                            ELSE
                                v_output_value := to_date(v_year || '0331', 'YYYYMMDD');
                            END IF;
                        ELSIF SUBSTR(p_value, v_first_char, 1) = '2' THEN
                            IF v_start = 'START' THEN
                                v_output_value := to_date(v_year || '0401', 'YYYYMMDD');
                            ELSE
                                v_output_value := to_date(v_year || '0630', 'YYYYMMDD');
                            END IF;
                        ELSIF SUBSTR(p_value, v_first_char, 1) = '3' THEN
                            IF v_start = 'START' THEN
                                v_output_value := to_date(v_year || '0701', 'YYYYMMDD');
                            ELSE
                                v_output_value := to_date(v_year || '0930', 'YYYYMMDD');
                            END IF;
                        ELSE
                            IF v_start = 'START' THEN
                                v_output_value := to_date(v_year || '1001', 'YYYYMMDD');
                            ELSE
                                v_output_value := to_date(v_year || '1231', 'YYYYMMDD');
                            END IF;
                        END IF;             
                    WHEN g_period_semester THEN
                        IF SUBSTR(p_value, v_first_char, 1) = '1' THEN
                            IF v_start = 'START' THEN
                                v_output_value := to_date(v_year || '0101', 'YYYYMMDD');
                            ELSE
                                v_output_value := to_date(v_year || '0630', 'YYYYMMDD');
                            END IF;
                        ELSE
                            IF v_start = 'START' THEN
                                v_output_value := to_date(v_year || '0701', 'YYYYMMDD');
                            ELSE
                                v_output_value := to_date(v_year || '1231', 'YYYYMMDD');
                            END IF;
                        END IF;
                    WHEN g_period_year THEN
                        IF v_start = 'START' THEN
                            v_output_value := to_date(v_year || '0101', 'YYYYMMDD');
                        ELSE
                            v_output_value := to_date(v_year || '1231', 'YYYYMMDD');
                        END IF;
                END CASE;                
        end case; 
      
        return v_output_value;
END;
$function$
;

------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_cast_to_timeperiod(p_value date, p_datatype character varying, p_mask character varying)
 RETURNS character varying
 LANGUAGE plpgsql
 IMMUTABLE STRICT
AS $function$
begin
    return vtl_cast_to_timeperiod(to_char(p_value, p_mask), p_datatype, p_mask);
END;
$function$
;

------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_cast_to_timeperiod(p_value character varying, p_datatype character varying, p_mask character varying)
 RETURNS character varying
 LANGUAGE plpgsql
 IMMUTABLE STRICT
AS $function$
declare
v_output_value VARCHAR(100);
v_retval VARCHAR(100);
g_period_day VARCHAR(1) := 'D';
begin
	case p_datatype 
            when 'DATE' then v_output_value :=  TO_CHAR(TO_DATE(p_value, p_mask), 'YYYY') || g_period_day || TO_CHAR(TO_DATE(p_value, p_mask), 'DDD');
            when 'STRING' then
            	v_retval := vtl_period_indicator(p_value);
            	IF v_retval = 'Invalid format' THEN
           			v_output_value :=  'Not castable value';
           		ELSE
           			v_output_value := p_value; 
           		END IF;
        end case;
  
        return v_output_value;
END;
$function$
;

------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_cast_to_string(p_value anyelement, p_datatype character varying, p_mask character varying)
 RETURNS character varying
 LANGUAGE plpgsql
 IMMUTABLE STRICT
AS $function$
declare
v_output_value VARCHAR(1000);
g_true_value VARCHAR(4) := 'TRUE';
g_false_value VARCHAR(5) := 'FALSE';
begin
	case p_datatype 
    	when 'INTEGER' then v_output_value :=  p_value;
        when 'NUMBER' then v_output_value :=  p_value; 
        when 'BOOLEAN' then 
                            case when UPPER(p_value) IN (g_true_value, '1') 
                                then v_output_value := '1'; 
                                else v_output_value := '0'; 
                            end case;
        when 'TIME' then v_output_value  := to_timestamp(p_value, p_mask);
        when 'DATE' then v_output_value  := to_date(p_value, p_mask);      
        when 'DURATION' then v_output_value := p_value;--to_char(p_value);
        when 'TIME_PERIOD' then v_output_value := p_value;
    end case; 
  
    return v_output_value;
END;
$function$
;

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_cast_to_string(p_value character varying, p_datatype character varying, p_mask character varying)
 RETURNS character varying
 LANGUAGE plpgsql
 IMMUTABLE STRICT
AS $function$
declare
v_output_value VARCHAR(1000);
g_true_value VARCHAR(4) := 'TRUE';
g_false_value VARCHAR(5) := 'FALSE';
begin
	case p_datatype 
    	when 'INTEGER' then v_output_value :=  p_value;
        when 'NUMBER' then v_output_value :=  p_value; 
        when 'BOOLEAN' then 
                            case when UPPER(p_value) IN (g_true_value, '1') 
                                then v_output_value := '1'; 
                                else v_output_value := '0'; 
                            end case;
        when 'TIME' then v_output_value  := to_timestamp(p_value, p_mask);
        when 'DATE' then v_output_value  := to_date(p_value, p_mask);      
        when 'DURATION' then v_output_value := p_value;--to_char(p_value);
        when 'TIME_PERIOD' then v_output_value := p_value;
    end case; 
  
    return v_output_value;
END;
$function$
;

------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_cast_to_string(p_value date, p_datatype character varying, p_mask character varying)
 RETURNS character varying
 LANGUAGE plpgsql
 IMMUTABLE STRICT
AS $function$declare
v_output_value VARCHAR(1000);

begin
	case p_datatype 
    	when 'DATE' then v_output_value  := to_char(p_value, p_mask);
    end case; 
  
    return v_output_value;
END;
$function$
;

------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_cast_to_duration(p_value character varying, p_datatype character varying, p_mask character varying)
 RETURNS character varying
 LANGUAGE plpgsql
 IMMUTABLE STRICT
AS $function$declare
V_LN_MASK NUMERIC;
V_LN_VALUE NUMERIC;
V_MASK VARCHAR(100);
V_VALUE VARCHAR(100);
V_VALUE_TMP VARCHAR(100);
V_VALUE_N NUMERIC;
V_P_MASK VARCHAR(100) := '';
V_P_VALUE VARCHAR(100) := '';
V_CAST VARCHAR(100) := 'OK';
V_OUTPUT_VALUE VARCHAR(100);
V_LOOP INTEGER := 1;
V_SEARCH_CHAR VARCHAR(1);
V_COUNT_PERIOD NUMERIC := 0;
PERIOD_SEPARATOR VARCHAR(10) := '\\';
begin
	V_OUTPUT_VALUE := p_value;
	V_VALUE := p_value;
	V_VALUE_TMP := p_value;
	V_MASK := p_mask;
	
	SELECT LENGTH(REPLACE(p_mask, PERIOD_SEPARATOR, '')) INTO V_LN_MASK;
	
	IF(V_LN_MASK <> LENGTH(p_value) OR (V_VALUE NOT LIKE 'P%') OR (V_MASK NOT LIKE '\\\\P%'))
	THEN
		V_CAST := 'KO';
	ELSE
		SELECT LENGTH(V_MASK) INTO V_LN_MASK;
		--RAISE NOTICE 'LUNGHEZZA MASK- %',V_LN_MASK;
		WHILE (V_LOOP < V_LN_MASK) LOOP
			V_P_MASK := V_P_MASK || SUBSTR(V_MASK, vtl_instr(V_MASK, PERIOD_SEPARATOR, V_LOOP) +2, 1);
			
			IF V_P_MASK IS NOT NULL
			THEN
				V_LOOP := vtl_instr(V_MASK, PERIOD_SEPARATOR, V_LOOP) + 2;
			ELSE
				V_LOOP := V_LN_MASK + 1; --EXIT LOOP
			END IF;
		END LOOP;
		
		SELECT LENGTH(V_VALUE) INTO V_LN_VALUE;
		SELECT LENGTH(V_P_MASK) INTO V_COUNT_PERIOD;
		V_LOOP := 1;
		
		WHILE (V_LOOP <= V_COUNT_PERIOD) LOOP
	    	V_SEARCH_CHAR := SUBSTR(V_P_MASK, V_LOOP, 1);
			V_P_VALUE := V_P_VALUE || SUBSTR(V_VALUE_TMP, STRPOS(V_VALUE_TMP, V_SEARCH_CHAR), 1);
			
			IF ((V_P_VALUE IS NOT NULL) AND STRPOS(V_VALUE_TMP, V_SEARCH_CHAR) > 0 )
			THEN
				V_VALUE_TMP := SUBSTR(V_VALUE_TMP, STRPOS(V_VALUE_TMP, V_SEARCH_CHAR));
				V_VALUE := REPLACE(V_VALUE, V_SEARCH_CHAR, '');
				
			END IF;
			V_LOOP := V_LOOP + 1;
		END LOOP;
		V_VALUE := REPLACE(V_VALUE, 'T', '');
														  
		IF (V_P_MASK = V_P_VALUE)
		THEN
			V_VALUE_N := CAST(V_VALUE AS NUMERIC);
		ELSE
			V_CAST := 'KO';
		END IF;
	END IF;
														  
	IF(V_CAST = 'KO')
	THEN
		RAISE EXCEPTION 'Invalid cast.';
	END IF;

    return V_OUTPUT_VALUE;
END;
$function$
;


------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.convert_to_date(p_timeval character varying)
 RETURNS date
 LANGUAGE plpgsql
 IMMUTABLE STRICT
AS $function$
declare
v_size INTEGER;
v_position INTEGER;
v_period VARCHAR(1);
v_retval DATE;
g_true_value VARCHAR(4) := 'TRUE';
g_false_value VARCHAR(5) := 'FALSE';
g_time_separator VARCHAR(1) := '/';
g_period_day VARCHAR(1) := 'D';
g_period_week VARCHAR(1) := 'W';
g_period_month VARCHAR(1) := 'M';
g_period_quarter VARCHAR(1) := 'Q';
g_period_semester VARCHAR(1) := 'S';
g_period_year VARCHAR(1) := 'A';
begin
	v_size := LENGTH(TRIM(p_timeval));

        CASE v_size
            WHEN 4 THEN
                --date YYYY format
                v_retval := to_date(p_timeval || '-01-01', 'yyyy-mm-dd');
            WHEN 10 THEN
                --date YYYY-MM-DD format
                v_retval := to_date(p_timeval, 'yyyy-mm-dd');
            ELSE
                IF v_size < 5 THEN
                    v_retval := null;
                ELSE
                    v_position := strpos(p_timeval, g_time_separator);
                    
                    IF v_position <> 0 THEN
                        --YYYY-MM/YYYY-MM format
                        v_retval := to_date(substr(p_timeval, 1, v_position -1) || '-01', 'yyyy-mm-dd');
                    ELSE
                       v_retval := vtl_cast_to_date(p_timeval, 'TIME_PERIOD', 'START');
                    END IF;
                END IF;
        END CASE;
  
    return v_retval;
   
   
   
END;
$function$
;

------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.convert_from_date(p_timeval date, p_period character varying, date_value character varying)
 RETURNS character varying
 LANGUAGE plpgsql
 IMMUTABLE STRICT
AS $function$
declare
v_position INTEGER;
v_retval VARCHAR(50);
g_true_value VARCHAR(4) := 'TRUE';
g_false_value VARCHAR(5) := 'FALSE';
g_time_separator VARCHAR(1) := '/';
g_period_day VARCHAR(1) := 'D';
g_period_week VARCHAR(1) := 'W';
g_period_month VARCHAR(1) := 'M';
g_period_quarter VARCHAR(1) := 'Q';
g_period_semester VARCHAR(1) := 'S';
g_period_year VARCHAR(1) := 'A';
g_period_date_time VARCHAR(1) := 'T';
BEGIN
        CASE p_period
            WHEN g_period_day THEN
                --YYYYDddd format
                v_retval := to_char(p_timeval, 'YYYY') || g_period_day || to_char(p_timeval, 'DDD');
            WHEN g_period_week THEN
                --YYYYWww format
                v_retval := to_char(p_timeval, 'YYYY') || g_period_week || to_char(p_timeval, 'WW');
            WHEN g_period_month THEN
                v_position := strpos(date_value, g_time_separator);
                
                IF v_position <> 0 THEN
                    --YYYY-MM/YYYY-MM format
                    v_retval := to_char(p_timeval, 'YYYY') || '-' || SUBSTR(date_value, 6, 2) || g_time_separator || to_char(p_timeval, 'YYYY') || '-' || SUBSTR(date_value, 14, 2);
                ELSE
                    --YYYYMmm format
                    v_retval := to_char(p_timeval, 'YYYY') || g_period_month || to_char(p_timeval, 'MM');
                END IF; 
            WHEN g_period_quarter THEN
                --YYYYQq format
                IF to_char(p_timeval, 'MM')::INTEGER <= 3 THEN
                    v_retval := to_char(p_timeval, 'YYYY') || g_period_quarter || '1';
                ELSIF to_char(p_timeval, 'MM')::INTEGER <= 6 THEN
                    v_retval := to_char(p_timeval, 'YYYY') || g_period_quarter || '2';
                ELSIF to_char(p_timeval, 'MM')::INTEGER <= 9 THEN
                    v_retval := to_char(p_timeval, 'YYYY') || g_period_quarter || '3';
                ELSE
                    v_retval := to_char(p_timeval, 'YYYY') || g_period_quarter || '4';
                END IF; 
            WHEN g_period_semester THEN
                --YYYYSs format
                IF to_char(p_timeval, 'MM')::INTEGER <= 6 THEN
                    v_retval := to_char(p_timeval, 'YYYY') || g_period_semester || '1';
                ELSE
                    v_retval := to_char(p_timeval, 'YYYY') || g_period_semester || '2';
                END IF; 
            WHEN g_period_year THEN
                --YYYYA format
                v_retval := to_char(p_timeval, 'YYYY');
            WHEN g_period_date_time THEN
                --YYYY-MM-DD
                v_retval := to_char(p_timeval, 'YYYY') || '-' || SUBSTR(date_value, 6, 5);
        END CASE;        
    
        RETURN v_retval;
   
END;
$function$
;


------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION sv_vtl_sql.vtl_instr(string character varying, string_to_search character varying, beg_index integer)
 RETURNS integer
 LANGUAGE plpgsql
 STRICT
AS $function$DECLARE
    pos integer NOT NULL DEFAULT 0;
    temp_str varchar;
    beg integer;
    length integer;
    ss_length integer;
BEGIN
    IF beg_index > 0 THEN
        temp_str := substring(string FROM beg_index);
        pos := position(string_to_search IN temp_str);
        IF pos = 0 THEN
            RETURN 0;
        ELSE
            RETURN pos + beg_index - 1;
        END IF;
    END IF;
END;
$function$
;


------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE sv_vtl_sql.vtl_fill_time_series(p_input_dataset character varying, p_id_list character varying, p_id_time character varying, p_limits_method character varying)
 LANGUAGE plpgsql
AS $procedure$
declare

--- Variabili
	v_input_dataset  VARCHAR (1000) := p_input_dataset; -- è il nome del dataset di input inviato come parametro nella call della procedura
    v_id_list VARCHAR (1000) := p_id_list;  -- è la lista degli identificativi (tranne l'ID di tipo TIME) del dataset di input inviato come parametro nella call della procedura
    v_id_time VARCHAR (1000) := p_id_time; -- è l'ID di tipo TIME del dataset di input inviato come parametro nella call della procedura
    v_limits_method VARCHAR (1000) := p_limits_method; -- valorizzato con SINGLE o con ALL

    v_if_exists_cycle_tab int; -- variabile che sarà valorizzata con 0 se non esiste la tabella da droppare, con 1 se esiste. Utilizzo questa variabile per non far andare in errore il DROP nel caso in cui non esiste la tabella FILL_TIME_SERIES_CYCLE
    v_if_exists_dsr_tab int;  -- variabile che sarà valorizzata con 0 se non esiste la tabella da droppare, con 1 se esiste. Utilizzo questa variabile per non far andare in errore il DROP nel caso in cui non esiste la tabella FILL_TIME_SERIES_DSR

    v_min_all_time DATE; --variabile che indica la data minima nel caso di ALL
    v_max_all_time DATE; --variabile che indica la data massima nel caso di ALL
    v_min_time VARCHAR(30); --variabile che utilizzerò nel ciclo di incremento dei time
    v_max_time VARCHAR(30); --variabile che utilizzerò nel ciclo di incremento dei time
    v_period VARCHAR(10); --variabile che utilizzerò nel ciclo di incremento dei time
    v_loop_time_1 VARCHAR(100); --variabile che utilizzerò nel ciclo di incremento dei time
    v_loop_time_2 VARCHAR(100); --variabile che utilizzerò nel ciclo di incremento dei time
    v_loop_row int := 1; -- per ciclare le righe della tabella "CURSORE". L'inizializzo a 1
    v_loop_row_end int;  -- per concludere il ciclo sulle righe della tabella "CURSORE"

    --- Scrivo in due variabili i nomi delle tabelle che crea la procedura. Utilizzo le variabili perchè sono più comode negli EXECUTE 
    v_cycle_tab_name VARCHAR (50) :=  'temporary_fts_cycle';--'TEMPORARY_FTS_CYCLE';  --- tabella che utilizzo come cursore per ciclare 
    v_dsr_tab_name VARCHAR (50) :=  'temporary_fts';--'TEMPORARY_FTS';  --- tabella dove scrivo il DATASET RISULTATO

    --- Sql statement per costruire il "CURSORE" in caso di v_limits_method='SINGLE'
    v_sql_stmt_single VARCHAR (2000) :=
    'CREATE TABLE '||v_cycle_tab_name||' AS 
    SELECT DISTINCT '
                 ||v_id_list||', '||
                 'VTL_PERIOD_INDICATOR('||v_id_time||') AS PERIOD, 
                 MAX('||v_id_time||') OVER (PARTITION BY '||v_id_list||', VTL_PERIOD_INDICATOR('||v_id_time||')) AS MAX_TIME, 
                 MIN('||v_id_time||') OVER (PARTITION BY '||v_id_list||',  VTL_PERIOD_INDICATOR('||v_id_time||')) AS MIN_TIME,
                 DENSE_RANK() OVER (ORDER BY '||v_id_list||', VTL_PERIOD_INDICATOR('||v_id_time||')) AS N_ROW
    FROM '||v_input_dataset||
    ' ORDER BY ' ||v_id_list||
                      ' , VTL_PERIOD_INDICATOR('||v_id_time||')';

    --- Sql statement per costruire il "CURSORE" in caso di v_limits_method='ALL'
    v_sql_stmt_all VARCHAR (2000) :=
    'CREATE TABLE '||v_cycle_tab_name||' AS 
    SELECT DISTINCT '
                 ||v_id_list||', '||
                 'VTL_PERIOD_INDICATOR('||v_id_time||') AS PERIOD, 
                 MIN('||v_id_time||') OVER (PARTITION BY '||v_id_list||',  VTL_PERIOD_INDICATOR('||v_id_time||')) AS MIN_TIME,
                 DENSE_RANK() OVER (ORDER BY '||v_id_list||', VTL_PERIOD_INDICATOR('||v_id_time||')) AS N_ROW
    FROM '||v_input_dataset||
    ' ORDER BY ' ||v_id_list||
                      ' , VTL_PERIOD_INDICATOR('||v_id_time||')';

    --- Sql statement per popolare la variabile v_loop_row_end contando le righe della tabella FILL_TIME_SERIES_CYCLE
    v_stmt_loop_row_end  VARCHAR (2000) := 'SELECT COUNT(*) FROM '||v_cycle_tab_name;

    --- Sql statement per creare la tabella DSR sulla base degli identificativi del DS di input
    v_stmt_create_dsr  VARCHAR (2000) := 
    'CREATE TABLE '||v_dsr_tab_name||' AS  
     SELECT DISTINCT '
                 ||v_id_list||', ' 
                 ||v_id_time||' 
     FROM '||v_input_dataset||' 
     limit 0';
    
BEGIN -- INIZIO DEL CORPO DELLA PROCEDURA
-- Popolo la variabile v_if_exists_cycle_tab
        EXECUTE 'SELECT COUNT(*) FROM information_schema.tables WHERE TABLE_NAME = ''temporary_fts_cycle'''
        INTO v_if_exists_cycle_tab;
        --USING v_cycle_tab_name;

        -- Popolo la variabile v_if_exists_dsr_tab
        EXECUTE  'SELECT COUNT(*) FROM information_schema.tables WHERE TABLE_NAME = ''temporary_fts'''     
        INTO v_if_exists_dsr_tab;
        --USING v_dsr_tab_name;

        -- se esiste la tabella "CURSORE" effettuo il DROP 
        IF v_if_exists_cycle_tab=1
        THEN
            EXECUTE  'DROP TABLE '||v_cycle_tab_name;
        END IF;

        -- se esiste la tabella DSR effettuo il DROP 
        IF v_if_exists_dsr_tab=1
        THEN
            EXECUTE  'DROP TABLE '||v_dsr_tab_name;
        END IF;

        -- CREO la tabella DSR. non avendo informazioni sui datatype creo la tabella a partire dal dataset di input
        EXECUTE  (v_stmt_create_dsr);

        --- creo un cursore differente a seconda del parametro v_limits_method (SINGLE/ALL)
        IF v_limits_method = 'SINGLE'
        THEN
            EXECUTE (v_sql_stmt_single);
            
            --- popolo la variabile v_loop_row_end contando le righe della tabella FILL_TIME_SERIES_CYCLE. La variabile mi fornirà la condizione di uscita dal ciclo sul "CURSORE"
            EXECUTE  (v_stmt_loop_row_end)
            INTO v_loop_row_end;
            
            -- faccio ciclare le righe del "CURSORE"   
            WHILE v_loop_row <= v_loop_row_end
            LOOP
                EXECUTE  'SELECT MIN_TIME FROM '||v_cycle_tab_name||' WHERE N_ROW = ' || v_loop_row
                INTO v_min_time;
                --USING v_loop_row;

                EXECUTE  'SELECT MAX_TIME FROM '||v_cycle_tab_name||' WHERE N_ROW = ' || v_loop_row   
                INTO v_max_time;
                --USING v_loop_row;
    
                v_loop_time_1 := VTL_TIMESHIFT(v_min_time, -1); 
                v_loop_time_2 := VTL_TIMESHIFT(v_min_time, -1);
    
                WHILE v_loop_time_2 <> v_max_time 
                LOOP
                    v_loop_time_2 := VTL_TIMESHIFT(v_loop_time_1, 1);
        
                    EXECUTE   'INSERT INTO '||v_dsr_tab_name||' 
                                                          SELECT '||v_id_list||', '||''''||v_loop_time_2||''''||'  
                                                          FROM '||v_cycle_tab_name||' 
                                                          WHERE N_ROW = ' || v_loop_row; 
                    --USING v_loop_row;
                    
                    --COMMIT;
        
                    v_loop_time_1 := v_loop_time_2;
                END LOOP;
    
                v_loop_row := v_loop_row + 1;                
            END LOOP;            
        ELSE
            EXECUTE  'SELECT MIN(convert_to_date('||v_id_time||')) FROM '||v_input_dataset
            INTO v_min_all_time;

            EXECUTE  'SELECT MAX(convert_to_date('||v_id_time||')) FROM '||v_input_dataset
            INTO v_max_all_time;
        
            EXECUTE (v_sql_stmt_all);

            --- popolo la variabile v_loop_row_end contando le righe della tabella FILL_TIME_SERIES_CYCLE. La variabile mi fornirà la condizione di uscita dal ciclo sul "CURSORE"
            EXECUTE  (v_stmt_loop_row_end)
            INTO v_loop_row_end;
            
            -- faccio ciclare le righe del "CURSORE"   
            WHILE v_loop_row <= v_loop_row_end
            LOOP
                EXECUTE  'SELECT MIN_TIME FROM '||v_cycle_tab_name||' WHERE N_ROW = ' || v_loop_row
                INTO v_min_time;
                --USING v_loop_row;
                
                EXECUTE  'SELECT PERIOD FROM '||v_cycle_tab_name||' WHERE N_ROW = ' || v_loop_row   
                INTO v_period;
                --USING v_loop_row;

                --IF v_period = g_period_date_time THEN
                    v_min_time := convert_from_date(v_min_all_time, v_period, v_min_time);

                    v_max_time := convert_from_date(v_max_all_time, v_period, v_min_time);                   
                --ELSE
                --    v_min_time := convert_from_date(v_min_all_time, v_period, null);

                --    v_max_time := convert_from_date(v_max_all_time, v_period, null);
                --END IF;

                v_loop_time_1 := VTL_TIMESHIFT(v_min_time, -1); 
                v_loop_time_2 := VTL_TIMESHIFT(v_min_time, -1);
    
                WHILE (v_loop_time_2 <> v_max_time and v_loop_time_2 < v_max_time)
                LOOP
                    v_loop_time_2 := VTL_TIMESHIFT(v_loop_time_1, 1);
        
                    EXECUTE   'INSERT INTO '||v_dsr_tab_name||' 
                                                          SELECT '||v_id_list||', '||''''||v_loop_time_2||''''||'  
                                                          FROM '||v_cycle_tab_name||' 
                                                          WHERE N_ROW = ' || v_loop_row;
                    --USING v_loop_row;
                    
                    --COMMIT;
        
                    v_loop_time_1 := v_loop_time_2;
                END LOOP;
    
                v_loop_row := v_loop_row + 1;                
            END LOOP;               
        END IF;

        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                NULL;
            WHEN OTHERS THEN
                -- Consider logging the error and then re-raise
                RAISE;              
               
END;
$procedure$
;


