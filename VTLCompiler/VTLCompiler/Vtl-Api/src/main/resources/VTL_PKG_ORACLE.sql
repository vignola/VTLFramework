CREATE OR REPLACE PACKAGE            VTL_PKG
AS
   /******************************************************************************
      NAME: VTL_PKG
      VERSION: 1.0
      DESCRIPTION: Package to store procedures and functions for Vtl Compiler.
   ******************************************************************************/

    --------------------------------------------------------------------------------
    -- Global variables
    g_true_value VARCHAR2(4) := 'TRUE';
    g_false_value VARCHAR2(5) := 'FALSE';
    g_time_separator VARCHAR2(1) := '/';
    g_period_day VARCHAR2(1) := 'D';
    g_period_week VARCHAR2(1) := 'W';
    g_period_month VARCHAR2(1) := 'M';
    g_period_quarter VARCHAR2(1) := 'Q';
    g_period_semester VARCHAR2(1) := 'S';
    g_period_year VARCHAR2(1) := 'A';
    g_period_date_time VARCHAR2(1) := 'T';

    --------------------------------------------------------------------------------
    -- Functions 
    FUNCTION is_number (p_string VARCHAR2) RETURN BOOLEAN;

    FUNCTION and_bool (p_left VARCHAR2, p_right VARCHAR2) RETURN VARCHAR2;

    FUNCTION or_bool (p_left VARCHAR2, p_right VARCHAR2) RETURN VARCHAR2;

    FUNCTION xor_bool (p_left VARCHAR2, p_right VARCHAR2) RETURN VARCHAR2;

    FUNCTION not_bool (p_param VARCHAR2) RETURN VARCHAR2;

    FUNCTION timeshift (p_timeval DATE, p_shiftval INTEGER) RETURN DATE;
    FUNCTION timeshift (p_timeval TIMESTAMP, p_shiftval INTEGER) RETURN TIMESTAMP;
    FUNCTION timeshift (p_timeval VARCHAR2, p_shiftval INTEGER) RETURN VARCHAR2;

    FUNCTION iso_week_to_date (p_iso_year IN INTEGER, p_iso_week IN INTEGER) RETURN DATE;

    FUNCTION period_indicator (p_timeval VARCHAR2) RETURN VARCHAR2;

    FUNCTION time_agg (p_timeval DATE, p_period_to VARCHAR2, p_period_from VARCHAR2, p_start VARCHAR2) RETURN DATE;    
    FUNCTION time_agg (p_timeval VARCHAR2, p_period_to VARCHAR2, p_period_from VARCHAR2, p_start VARCHAR2) RETURN VARCHAR2;

    FUNCTION standardize_number (p_value VARCHAR2) RETURN NUMBER; 

    FUNCTION cast_to_int (p_value VARCHAR2, p_datatype VARCHAR2) RETURN INTEGER;
    FUNCTION cast_to_number (p_value VARCHAR2, p_datatype VARCHAR2, p_mask VARCHAR2) return NUMBER;
    FUNCTION cast_to_boolean (p_value VARCHAR2, p_datatype VARCHAR2) RETURN VARCHAR2;
    FUNCTION cast_to_time (p_value DATE, p_datatype VARCHAR2, p_mask VARCHAR2) return VARCHAR2;
    FUNCTION cast_to_time (p_value VARCHAR2, p_datatype VARCHAR2, p_mask VARCHAR2) return VARCHAR2;
    FUNCTION cast_to_date (p_value VARCHAR2, p_datatype VARCHAR2, p_mask VARCHAR2) return DATE;
    FUNCTION cast_to_timeperiod (p_value DATE, p_datatype VARCHAR2, p_mask VARCHAR2) RETURN VARCHAR2;
    FUNCTION cast_to_timeperiod (p_value VARCHAR2, p_datatype VARCHAR2, p_mask VARCHAR2) RETURN VARCHAR2;
    FUNCTION cast_to_string (p_value DATE, p_datatype VARCHAR2, p_mask VARCHAR2) RETURN VARCHAR2;
    FUNCTION cast_to_string (p_value VARCHAR2, p_datatype VARCHAR2, p_mask VARCHAR2) RETURN VARCHAR2;
    FUNCTION cast_to_duration (p_value VARCHAR2, p_datatype VARCHAR2, p_mask VARCHAR2) RETURN VARCHAR2;

    FUNCTION convert_to_date (p_timeval DATE) RETURN DATE;
    FUNCTION convert_to_date (p_timeval TIMESTAMP) RETURN DATE;
    FUNCTION convert_to_date (p_timeval VARCHAR2) return DATE;
    
    FUNCTION convert_from_date (p_timeval DATE, p_period VARCHAR2, date_value VARCHAR2) RETURN VARCHAR2;

    -- Procedures
    PROCEDURE fill_time_series (p_input_dataset VARCHAR2, p_id_list VARCHAR2, p_id_time VARCHAR2, p_limits_method VARCHAR2);

      -- Exception
    INVALID_CAST     EXCEPTION;
END;
/


create or replace PACKAGE BODY            VTL_PKG
AS
    -- Functions 
    --------------------------------------------------------------------------------
    FUNCTION is_number (p_string VARCHAR2) RETURN BOOLEAN IS
        v_new_num NUMBER;
    BEGIN
		v_new_num := TO_NUMBER(p_string);
        RETURN TRUE;

    EXCEPTION
        WHEN VALUE_ERROR THEN
            RETURN FALSE;    
	END is_number;
    --------------------------------------------------------------------------------    
    FUNCTION and_bool (p_left VARCHAR2, p_right VARCHAR2) RETURN VARCHAR2 IS
        v_left_bool BOOLEAN;
        v_right_bool BOOLEAN;
        v_res_bool BOOLEAN;
        v_retval VARCHAR2(10);
    BEGIN
		IF p_left IS NULL THEN
			v_left_bool := null;
		ELSE
			v_left_bool := case when p_left = g_true_value then true else false end;
		END IF;

		IF p_right IS NULL THEN
			v_right_bool := null;
		ELSE
			v_right_bool := case when p_right = g_true_value then true else false end;
		END IF;

        --apply AND operator to boolean values
        v_res_bool := v_left_bool AND v_right_bool;

        IF v_res_bool IS NULL THEN
            v_retval := null;
        ELSE
            v_retval := case when v_res_bool THEN g_true_value else g_false_value end;
        END IF;

        RETURN v_retval;
	END and_bool;
    --------------------------------------------------------------------------------    
    FUNCTION or_bool (p_left VARCHAR2, p_right VARCHAR2) RETURN VARCHAR2 IS
        v_left_bool BOOLEAN;
        v_right_bool BOOLEAN;
        v_res_bool BOOLEAN;
        v_retval VARCHAR2(10);
    BEGIN
		IF p_left IS NULL THEN
			v_left_bool := null;
		ELSE
			v_left_bool := case when p_left = g_true_value then true else false end;
		END IF;

		IF p_right IS NULL THEN
			v_right_bool := null;
		ELSE
			v_right_bool := case when p_right = g_true_value then true else false end;
		END IF;

        --apply OR operator to boolean values
        v_res_bool := v_left_bool OR v_right_bool;

        IF v_res_bool IS NULL THEN
            v_retval := null;
        ELSE
            v_retval := case when v_res_bool THEN g_true_value else g_false_value end;
        END IF;

        RETURN v_retval;
	END or_bool;
    --------------------------------------------------------------------------------    
    FUNCTION xor_bool (p_left VARCHAR2, p_right VARCHAR2) RETURN VARCHAR2 IS
        v_retval VARCHAR2(10);
    BEGIN
		IF p_left IS NULL OR p_right IS NULL THEN
			v_retval := null;
		ELSE
            IF p_left = p_right THEN
                v_retval := g_false_value;
            ELSE
                v_retval := g_true_value;
            END IF;
		END IF;

        RETURN v_retval;
	END xor_bool;
    --------------------------------------------------------------------------------    
    FUNCTION not_bool (p_param VARCHAR2) RETURN VARCHAR2 IS
	BEGIN
    	IF p_param IS NULL THEN
			RETURN NULL;
		ELSE
            IF p_param = g_true_value THEN
                RETURN g_false_value;
            ELSE
                RETURN g_true_value;
            END IF;
		END IF;

	END not_bool;
	--------------------------------------------------------------------------------
	FUNCTION timeshift (p_timeval DATE, p_shiftval INTEGER) RETURN DATE IS
        v_retval DATE;
	BEGIN
		v_retval := ADD_MONTHS(p_timeval, p_shiftval * 12);

        RETURN v_retval;
	END timeshift;
	--------------------------------------------------------------------------------
	FUNCTION timeshift (p_timeval TIMESTAMP, p_shiftval INTEGER) RETURN TIMESTAMP IS
        v_retval TIMESTAMP;
	BEGIN
		v_retval := ADD_MONTHS(p_timeval, p_shiftval * 12);

        RETURN v_retval;
	END timeshift;
    --------------------------------------------------------------------------------
	FUNCTION timeshift (p_timeval VARCHAR2, p_shiftval INTEGER) RETURN VARCHAR2 IS
        v_size INTEGER;
        v_position INTEGER;
        v_period VARCHAR2(1);
        v_leftval VARCHAR2(50);
        v_rightval VARCHAR2(50);
        v_retval VARCHAR2(50);
	BEGIN
        IF p_timeval = 'NULL' THEN
            return null;
        END IF; 

        v_size := LENGTH(p_timeval);

        CASE v_size
          WHEN 4 THEN
            --date YYYY format
            v_retval := p_timeval + p_shiftval;
          WHEN 10 THEN
            --date YYYY-MM-DD format
            v_retval := to_char(timeshift(to_date(p_timeval, 'YYYY-MM-DD'), p_shiftval), 'YYYY-MM-DD');
          ELSE 
            IF v_size < 5 THEN
                v_retval := 'Invalid format';
            ELSE
                v_position := INSTR(p_timeval, g_time_separator);
                IF v_position <> 0 THEN
                    --In this case the increase is annual and not monthly
                    v_leftval := timeshift(SUBSTR(p_timeval, 1, v_position - 1), p_shiftval * 12);
                    v_rightval := timeshift(SUBSTR(p_timeval, v_position + 1), p_shiftval * 12);
                    v_retval := v_leftval || g_time_separator || v_rightval;
                ELSE
                    v_period := SUBSTR(p_timeval, 5, 1);

                    IF v_period = '-' THEN
                        v_period := SUBSTR(p_timeval, 6, 1);

                        CASE v_period
                            WHEN g_period_day THEN
                                --date YYYY-Dddd format
                                v_retval := to_char(to_date(SUBSTR(p_timeval, 1, 4) || SUBSTR(p_timeval, 7, 3), 'YYYYDDD') + p_shiftval, 'YYYYDDD');
                                v_retval := SUBSTR(v_retval, 1, 4) || '-' || g_period_day || SUBSTR(v_retval, 5, 3);
                            WHEN g_period_week THEN
                                --date YYYY-Www format
                                v_retval := to_char(iso_week_to_date(SUBSTR(p_timeval, 1, 4), SUBSTR(p_timeval, 7, 2)) + (p_shiftval * 7), 'YYYYWW');
                                v_retval := SUBSTR(v_retval, 1, 4) || '-' || g_period_week || SUBSTR(v_retval, 5, 2);
                            WHEN g_period_month THEN
                                --period YYYY-Mmm format
                                v_retval := to_char(ADD_MONTHS(to_date(SUBSTR(p_timeval, 1, 4) || SUBSTR(p_timeval, 7, 2), 'YYYYMM'), p_shiftval), 'YYYYMM');
                                v_retval := SUBSTR(v_retval, 1, 4) || '-' || g_period_month || SUBSTR(v_retval, 5, 2);
                            WHEN g_period_quarter THEN
                                --date YYYY-Qq format
                                v_retval := to_char(ADD_MONTHS(to_date(SUBSTR(p_timeval, 1, 4) || SUBSTR(p_timeval, 7, 1)*3, 'YYYYMM'), p_shiftval*3), 'YYYYMM');
                                v_retval := SUBSTR(v_retval, 1, 4) || '-' || g_period_quarter || SUBSTR(v_retval, 5, 2)/3;
                            WHEN g_period_semester THEN
                                --date YYYY-Ss format
                                v_retval := to_char(ADD_MONTHS(to_date(SUBSTR(p_timeval, 1, 4) || SUBSTR(p_timeval, 7, 1)*6, 'YYYYMM'), p_shiftval*6), 'YYYYMM');
                                v_retval := SUBSTR(v_retval, 1, 4) || '-' || g_period_semester || SUBSTR(v_retval, 5, 2)/6; 
                            WHEN g_period_year THEN
                                --period YYYY-A format
                                v_retval := SUBSTR(p_timeval, 1, 4) + p_shiftval || '-' || g_period_year;
                            ELSE 
                                IF v_size = 7 THEN
                                    --date YYYY-mm format
                                    v_retval := to_char(ADD_MONTHS(to_date(p_timeval, 'YYYY-MM'), p_shiftval), 'YYYY-MM');
                                ELSE
                                    IF v_size = 8 THEN
                                        --date YYYY-xMM format
                                        v_retval := to_char(ADD_MONTHS(to_date(SUBSTR(p_timeval, 1, 4) || SUBSTR(p_timeval, 7, 2), 'YYYYMM'), p_shiftval), 'YYYYMM');
                                        v_retval := SUBSTR(v_retval, 1, 4) || SUBSTR(p_timeval, 5, 2) || SUBSTR(v_retval, 5, 2); 
                                    ELSE
                                        v_retval := 'Invalid format';
                                    END IF; 
                                END IF;    
                        END CASE;
                    ELSE
                        CASE v_period
                            WHEN g_period_day THEN
                                --date YYYYDddd format
                                v_retval := to_char(to_date(SUBSTR(p_timeval, 1, 4) || SUBSTR(p_timeval, 6, 3), 'YYYYDDD') + p_shiftval, 'YYYYDDD');
                                v_retval := SUBSTR(v_retval, 1, 4) || g_period_day || SUBSTR(v_retval, 5, 3);
                            WHEN g_period_week THEN
                                --date YYYYWww format
                                v_retval := to_char(iso_week_to_date(SUBSTR(p_timeval, 1, 4), SUBSTR(p_timeval, 6, 2)) + (p_shiftval * 7), 'YYYYWW');
                                v_retval := SUBSTR(v_retval, 1, 4) || g_period_week || SUBSTR(v_retval, 5, 2);
                            WHEN g_period_month THEN
                                --period YYYYMmm format
                                v_retval := to_char(ADD_MONTHS(to_date(SUBSTR(p_timeval, 1, 4) || SUBSTR(p_timeval, 6, 2), 'YYYYMM'), p_shiftval), 'YYYYMM');
                                v_retval := SUBSTR(v_retval, 1, 4) || g_period_month || SUBSTR(v_retval, 5, 2);
                            WHEN g_period_quarter THEN
                                --date YYYYQq format
                                v_retval := to_char(ADD_MONTHS(to_date(SUBSTR(p_timeval, 1, 4) || SUBSTR(p_timeval, 6, 1)*3, 'YYYYMM'), p_shiftval*3), 'YYYYMM');
                                v_retval := SUBSTR(v_retval, 1, 4) || g_period_quarter || SUBSTR(v_retval, 5, 2)/3;
                            WHEN g_period_semester THEN
                                --date YYYYSs format
                                v_retval := to_char(ADD_MONTHS(to_date(SUBSTR(p_timeval, 1, 4) || SUBSTR(p_timeval, 6, 1)*6, 'YYYYMM'), p_shiftval*6), 'YYYYMM');
                                v_retval := SUBSTR(v_retval, 1, 4) || g_period_semester || SUBSTR(v_retval, 5, 2)/6; 
                            WHEN g_period_year THEN
                                --period YYYYA format
                                v_retval := SUBSTR(p_timeval, 1, 4) + p_shiftval || g_period_year;
                            ELSE 
                                IF v_size = 6 THEN
                                    --date YYYYMM format
                                    v_retval := to_char(ADD_MONTHS(to_date(p_timeval, 'YYYYMM'), p_shiftval), 'YYYYMM');
                                ELSE
                                    IF v_size = 7 THEN
                                        --date YYYYxMM format
                                        v_retval := to_char(ADD_MONTHS(to_date(SUBSTR(p_timeval, 1, 4) || SUBSTR(p_timeval, 6, 2), 'YYYYMM'), p_shiftval), 'YYYYMM');
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
	END timeshift;
    --------------------------------------------------------------------------------    
    FUNCTION iso_week_to_date (p_iso_year IN INTEGER, p_iso_week IN INTEGER) RETURN DATE IS
        v_jan4_of_iso_year DATE;
        v_first_day_of_iso_year DATE;
        v_iso_date DATE;
        v_iso_date_iso_year INTEGER;
    BEGIN
        -- Find the first day of iso_year
        -- (= the Monday of the week containing January 4th)
        v_jan4_of_iso_year := TO_DATE(p_iso_year || '-01-04', 'YYYY-MM-DD');
        v_first_day_of_iso_year := TRUNC(v_jan4_of_iso_year, 'IW');

        -- Add the ISO week (in days)
        v_iso_date := v_first_day_of_iso_year + 7 * (p_iso_week - 1);

        -- Check whether iso_week is a valid ISO week
        -- (= whether the Thursday of the week containing iso_date is contained in the year iso_year)
        v_iso_date_iso_year := TO_CHAR(v_iso_date, 'IYYY');
        IF v_iso_date_iso_year <> p_iso_year THEN
            RAISE VALUE_ERROR;
        END IF;

        RETURN v_iso_date;
    END iso_week_to_date;
    -------------------------------------------------------------------------------- 
    FUNCTION period_indicator (p_timeval VARCHAR2) RETURN VARCHAR2 IS
        v_size INTEGER;
        v_period VARCHAR2(1);
        v_retval VARCHAR2(50);
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
            --date YYYY-MM-DD format
            v_retval := g_period_date_time;
          ELSE 
            IF v_size < 5 THEN
                v_retval := 'Invalid format';
            ELSE
                v_period := SUBSTR(p_timeval, 5, 1);

                IF v_period = '-' THEN
                    --SDMX v2.1 Time Period format
                    v_period := SUBSTR(p_timeval, 6, 1);

                    IF is_number(v_period) THEN
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
                    IF is_number(v_period) THEN
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
	END period_indicator;    
    --------------------------------------------------------------------------------
    FUNCTION time_agg (p_timeval DATE, p_period_to VARCHAR2, p_period_from VARCHAR2, p_start VARCHAR2) RETURN DATE IS
        v_retval DATE;
	BEGIN
		v_retval := to_date(time_agg(to_char(p_timeval, 'YYYY-MM-DD'), p_period_to, p_period_from, p_start), 'YYYY-MM-DD');

        RETURN v_retval;
	END time_agg;    
    --------------------------------------------------------------------------------
    FUNCTION time_agg (p_timeval VARCHAR2, p_period_to VARCHAR2, p_period_from VARCHAR2, p_start VARCHAR2) RETURN VARCHAR2 IS
        v_size INTEGER;
        v_position INTEGER;
        v_period VARCHAR2(1);
        v_start VARCHAR2(10);
        v_retval VARCHAR2(50);
	BEGIN
        IF p_timeval = 'NULL' THEN
            return null;
        END IF; 

        v_start := nvl(p_start, 'first');

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
                        v_retval := to_char(TRUNC(to_date(p_timeval,'YYYY-MM-DD'), 'DAY'), 'YYYY-MM-DD');
                    ELSE
                        v_retval := to_char(TRUNC(to_date(p_timeval,'YYYY-MM-DD'), 'DAY') + 6, 'YYYY-MM-DD');
                    END IF;
                WHEN g_period_month THEN
                    IF v_start = 'first' THEN
                        v_retval := to_char(last_day(to_date(p_timeval,'YYYY-MM-DD')), 'YYYY-MM') || '-01';
                    ELSE
                        v_retval := to_char(last_day(to_date(p_timeval,'YYYY-MM-DD')), 'YYYY-MM-DD');
                    END IF;
                WHEN g_period_quarter THEN
                    IF SUBSTR(p_timeval, 6, 2) <= 3 THEN
                        IF v_start = 'first' THEN
                            v_retval := SUBSTR(p_timeval, 1, 4) || '-01-01';
                        ELSE
                            v_retval := SUBSTR(p_timeval, 1, 4) || '-03-31';
                        END IF;
                    ELSIF SUBSTR(p_timeval, 6, 2) <= 6 THEN
                        IF v_start = 'first' THEN
                            v_retval := SUBSTR(p_timeval, 1, 4) || '-04-01';
                        ELSE
                            v_retval := SUBSTR(p_timeval, 1, 4) || '-06-30';
                        END IF;
                    ELSIF SUBSTR(p_timeval, 6, 2) <= 9 THEN
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
                    IF SUBSTR(p_timeval, 6, 2) <= 6 THEN
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
                v_position := INSTR(p_timeval, g_time_separator);
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
                                    v_retval := SUBSTR(p_timeval, 1, 4) || g_period_month || to_char(to_date(SUBSTR(p_timeval, 1, 4)|| '0101', 'YYYYMMDD') + SUBSTR(p_timeval, 7, 3) -1, 'MM');
                                ELSIF p_period_to = g_period_week THEN
                                    v_retval := SUBSTR(p_timeval, 1, 4) || g_period_week || to_char(to_date(SUBSTR(p_timeval, 1, 4)|| '0101', 'YYYYMMDD') + SUBSTR(p_timeval, 7, 3) -1, 'IW');
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
                                    v_retval := SUBSTR(p_timeval, 1, 4) || g_period_month || to_char(iso_week_to_date(SUBSTR(p_timeval, 1, 4), SUBSTR(p_timeval, 7, 2)), 'MM');
                                ELSE
                                    v_retval := p_timeval;
                                END IF;
                            WHEN g_period_month THEN
                                --YYYY-Mmm format
                                IF p_period_to = g_period_year THEN
                                    v_retval := SUBSTR(p_timeval, 1, 4);
                                ELSIF p_period_to = g_period_semester THEN
                                    IF SUBSTR(p_timeval, 7, 2) <= 6 THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '1';
                                    ELSE
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '2';
                                    END IF;
                                ELSIF p_period_to = g_period_quarter THEN
                                    IF SUBSTR(p_timeval, 7, 2) <= 3 THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '1';
                                    ELSIF SUBSTR(p_timeval, 7, 2) <= 6 THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '2';
                                    ELSIF SUBSTR(p_timeval, 7, 2) <= 9 THEN
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
                                    IF SUBSTR(p_timeval, 7, 2) <= 2 THEN
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
                                        IF SUBSTR(p_timeval, 6, 2) <= 6 THEN
                                            v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '1';
                                        ELSE
                                            v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '2';
                                        END IF;
                                    ELSIF p_period_to = g_period_quarter THEN
                                        IF SUBSTR(p_timeval, 6, 2) <= 3 THEN
                                            v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '1';
                                        ELSIF SUBSTR(p_timeval, 6, 2) <= 6 THEN
                                            v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '2';
                                        ELSIF SUBSTR(p_timeval, 6, 2) <= 9 THEN
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
                                            IF SUBSTR(p_timeval, 7, 2) <= 6 THEN
                                                v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '1';
                                            ELSE
                                                v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '2';
                                            END IF;
                                        ELSIF p_period_to = g_period_quarter THEN
                                            IF SUBSTR(p_timeval, 7, 2) <= 3 THEN
                                                v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '1';
                                            ELSIF SUBSTR(p_timeval, 7, 2) <= 6 THEN
                                                v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '2';
                                            ELSIF SUBSTR(p_timeval, 7, 2) <= 9 THEN
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
                                    v_retval := SUBSTR(p_timeval, 1, 4) || g_period_month || to_char(to_date(SUBSTR(p_timeval, 1, 4)|| '0101', 'YYYYMMDD') + SUBSTR(p_timeval, 6, 3) -1, 'MM');
                                ELSIF p_period_to = g_period_week THEN
                                    v_retval := SUBSTR(p_timeval, 1, 4) || g_period_week || to_char(to_date(SUBSTR(p_timeval, 1, 4)|| '0101', 'YYYYMMDD') + SUBSTR(p_timeval, 6, 3) -1, 'IW');
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
                                    v_retval := SUBSTR(p_timeval, 1, 4) || g_period_month || to_char(iso_week_to_date(SUBSTR(p_timeval, 1, 4), SUBSTR(p_timeval, 6, 2)), 'MM');
                                ELSE
                                    v_retval := p_timeval;
                                END IF;
                            WHEN g_period_month THEN
                                --YYYYMmm format
                                IF p_period_to = g_period_year THEN
                                    v_retval := SUBSTR(p_timeval, 1, 4);
                                ELSIF p_period_to = g_period_semester THEN
                                    IF SUBSTR(p_timeval, 6, 2) <= 6 THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '1';
                                    ELSE
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '2';
                                    END IF;
                                ELSIF p_period_to = g_period_quarter THEN
                                    IF SUBSTR(p_timeval, 6, 2) <= 3 THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '1';
                                    ELSIF SUBSTR(p_timeval, 6, 2) <= 6 THEN
                                        v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '2';
                                    ELSIF SUBSTR(p_timeval, 6, 2) <= 9 THEN
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
                                    IF SUBSTR(p_timeval, 6, 2) <= 2 THEN
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
                                        IF SUBSTR(p_timeval, 5, 2) <= 6 THEN
                                            v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '1';
                                        ELSE
                                            v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '2';
                                        END IF;
                                    ELSIF p_period_to = g_period_quarter THEN
                                        IF SUBSTR(p_timeval, 5, 2) <= 3 THEN
                                            v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '1';
                                        ELSIF SUBSTR(p_timeval, 5, 2) <= 6 THEN
                                            v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '2';
                                        ELSIF SUBSTR(p_timeval, 5, 2) <= 9 THEN
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
                                            IF SUBSTR(p_timeval, 6, 2) <= 6 THEN
                                                v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '1';
                                            ELSE
                                                v_retval := SUBSTR(p_timeval, 1, 4) || g_period_semester || '2';
                                            END IF;
                                        ELSIF p_period_to = g_period_quarter THEN
                                            IF SUBSTR(p_timeval, 6, 2) <= 3 THEN
                                                v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '1';
                                            ELSIF SUBSTR(p_timeval, 6, 2) <= 6 THEN
                                                v_retval := SUBSTR(p_timeval, 1, 4) || g_period_quarter || '2';
                                            ELSIF SUBSTR(p_timeval, 6, 2) <= 9 THEN
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
	END time_agg;    
    --------------------------------------------------------------------------------    
    FUNCTION standardize_number (p_value VARCHAR2) RETURN NUMBER IS
        v_standard NUMBER; 
        v_perc BOOLEAN;
        v_numeratore NUMBER;
        v_denominatore NUMBER;
        v_power INT;
        v_int  BOOLEAN;

        --- la funzione non ammette che i numeri in formato stringa contengano separatori delle migliaia.
        --- � indifferente che il separatore dei decimali sia un punto o una virgola

    BEGIN
        v_perc := CASE WHEN INSTR(p_value,'%',1) = 0 THEN FALSE ELSE TRUE END; --- mi dice se � una percentuale
        v_int := CASE WHEN INSTR(REPLACE(REPLACE(p_value, '.',g_time_separator),',',g_time_separator),g_time_separator,-1)=0 THEN TRUE ELSE FALSE END;
        v_numeratore := TO_NUMBER(REPLACE(REPLACE (REPLACE (p_value, '%', ''), '.', ''),',',''));        --tolgo '%' e punti e virgole 

        v_power := CASE WHEN v_perc = TRUE 
                        THEN CASE WHEN v_int = TRUE 
                        		  THEN 0
                        		  ELSE LENGTH(p_value) - INSTR(REPLACE(REPLACE(p_value, '.',g_time_separator),',',g_time_separator),g_time_separator,-1)-1
                        	 END
                        ELSE CASE WHEN v_int = TRUE 
                        		  THEN 0
                        		  ELSE LENGTH(p_value) - INSTR(REPLACE(REPLACE(p_value, '.',g_time_separator),',',g_time_separator),g_time_separator,-1)
                             END 
                   END; 
        v_denominatore := POWER(10,v_power); 
        v_standard := CASE WHEN v_perc = TRUE 
                                              THEN v_numeratore/(100*v_denominatore)
                                              else v_numeratore/(v_denominatore)
                                     END;

        RETURN v_standard ;
    EXCEPTION
        WHEN NO_DATA_FOUND
           THEN
              NULL;
        WHEN OTHERS
           THEN
              -- Consider logging the error and then re-raise
              RAISE;
    END standardize_number;       
    --------------------------------------------------------------------------------  
    FUNCTION cast_to_int (p_value VARCHAR2, p_datatype VARCHAR2) RETURN INTEGER IS
        v_output_value INTEGER; 

    BEGIN 
        case p_datatype 
            when 'STRING' then 
            v_output_value :=  REPLACE(p_value, '.', ',');
            when 'NUMBER' then v_output_value :=  standardize_number(p_value); 
            when 'BOOLEAN' then
                case 
                    when UPPER(p_value) IN ( g_true_value, '1') 
                        then v_output_value := 1; 
                        else v_output_value := 0; 
                end case;
        end case; 

        return v_output_value;
    END cast_to_int;
    --------------------------------------------------------------------------------  
    FUNCTION cast_to_number (p_value varchar2, p_datatype varchar2, p_mask varchar2) return NUMBER as 
    	v_output_value NUMBER; 

    BEGIN
        case p_datatype 
            when 'BOOLEAN' then 
                case 
                    when UPPER(p_value) IN (g_true_value, '1') 
                        then v_output_value := 1; 
                        else v_output_value := 0; 
                end case;
            when 'STRING' then 
           		IF TO_NUMBER(p_value) > TO_NUMBER(REPLACE(p_mask,'D','9')) THEN
           			v_output_value := p_mask;
           		ELSE
            		v_output_value := p_value;
            	END IF;
            when 'INTEGER' then v_output_value := standardize_number(p_value);
        end case; 

        return v_output_value;
    END cast_to_number;
    --------------------------------------------------------------------------------  
    FUNCTION cast_to_boolean (p_value VARCHAR2, p_datatype VARCHAR2) RETURN VARCHAR2 IS
        v_output_value VARCHAR2(5); 

    BEGIN
        case p_datatype 
            when 'INTEGER' then 
                case 
                    --when p_value = '1' then v_output_value := g_true_value;
                    --else v_output_value := g_false_value;
                    when p_value <> '0' then v_output_value := g_true_value;
                    else v_output_value := g_false_value;
                end case;
            when 'NUMBER' then 
                case 
                    --when p_value = '1' then v_output_value := g_true_value;
                    --else v_output_value := g_false_value;
                    when p_value <> '0' then v_output_value := g_true_value;
                    else v_output_value := g_false_value;
                end case;             
        end case; 

        return v_output_value;
    END cast_to_boolean;
    -------------------------------------------------------------------------------- 
    FUNCTION cast_to_time (p_value date, p_datatype varchar2, p_mask varchar2) return VARCHAR2 as 
        v_output_value VARCHAR2(100);
        
    BEGIN 
        case p_datatype 
            when 'DATE' then 
                v_output_value := to_char(p_value, 'YYYY-MM-DD') || g_time_separator || to_char(p_value, 'YYYY-MM-DD');
        end case; 

        return v_output_value;
    END cast_to_time;
    -------------------------------------------------------------------------------- 
    FUNCTION cast_to_time (p_value varchar2, p_datatype varchar2, p_mask varchar2) return VARCHAR2 as 
        v_period VARCHAR2(1);
        v_year VARCHAR2(4);
        v_position INTEGER;
        v_left_mask VARCHAR2(50);
        v_right_mask VARCHAR2(50);
        v_left_value VARCHAR2(50);
        v_righ_value VARCHAR2(50);
        v_first_char INTEGER;
        v_output_value VARCHAR2(100);

    BEGIN 
        case p_datatype 
            WHEN 'STRING' THEN 
                IF length(p_value) <> length( p_mask) -- DM
                THEN 
                    RAISE INVALID_CAST;
                END IF;
                    
                v_position := INSTR(p_mask, g_time_separator);
                IF v_position <> 0 THEN
                    v_left_mask := TRIM(SUBSTR(p_mask, 1, v_position - 1));
                    v_right_mask := TRIM(SUBSTR(p_mask, v_position + 1));

                    v_position := INSTR(p_value, g_time_separator);

                    v_left_value := TRIM(SUBSTR(p_value, 1, v_position - 1));
                    v_righ_value := TRIM(SUBSTR(p_value, v_position + 1));

                    v_output_value := to_char(TO_DATE(v_left_value, v_left_mask), v_left_mask) || g_time_separator || to_char(TO_DATE(v_righ_value, v_right_mask), v_right_mask);
                ELSE
                     RAISE INVALID_CAST;
                END IF;
            when 'DATE' then v_output_value := to_char(to_date(p_value, 'YYYY-MM-DD'), 'YYYY-MM-DD') || g_time_separator || to_char(to_date(p_value, 'YYYY-MM-DD'), 'YYYY-MM-DD');
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
                        v_output_value := to_char(to_date(v_year || SUBSTR(p_value, v_first_char, 3), 'YYYYDDD'), 'YYYY-MM-DD') || g_time_separator || to_char(to_date(v_year || SUBSTR(p_value, v_first_char, 3), 'YYYYDDD'), 'YYYY-MM-DD');
                    WHEN g_period_week THEN
                        --v_output_value := to_char(trunc(to_date(v_year || to_char(SUBSTR(p_value, v_first_char, 2) * 7, '000'), 'YYYYDDD'), 'IW'), 'YYYY-MM-DD') || g_time_separator || to_char(trunc(to_date(v_year || to_char(SUBSTR(p_value, v_first_char, 2) * 7, '000'), 'YYYYDDD'), 'IW') +6, 'YYYY-MM-DD');
                    	v_output_value := VTL_PKG.iso_week_to_date(v_year, SUBSTR(p_value, v_first_char, 2)) || g_time_separator || (VTL_PKG.iso_week_to_date(v_year, SUBSTR(p_value, v_first_char, 2)) + 6);
                    WHEN g_period_month THEN
                        v_output_value := to_date(v_year || SUBSTR(p_value, v_first_char, 2) || '01', 'YYYYMMDD') || g_time_separator || last_day(to_date(v_year || SUBSTR(p_value, v_first_char, 2) || '01', 'YYYYMMDD'));
                    WHEN g_period_quarter THEN
                        IF SUBSTR(p_value, v_first_char, 1) = '1' THEN
                            v_output_value := to_date(v_year || '0101', 'YYYYMMDD') || g_time_separator || to_date(v_year || '0331', 'YYYYMMDD');
                        ELSIF SUBSTR(p_value, v_first_char, 1) = '2' THEN
                            v_output_value := to_date(v_year || '0401', 'YYYYMMDD') || g_time_separator || to_date(v_year || '0630', 'YYYYMMDD');
                        ELSIF SUBSTR(p_value, v_first_char, 1) = '3' THEN
                            v_output_value := to_date(v_year || '0701', 'YYYYMMDD') || g_time_separator || to_date(v_year || '0930', 'YYYYMMDD');
                        ELSE
                            v_output_value := to_date(v_year || '1001', 'YYYYMMDD') || g_time_separator || to_date(v_year || '1231', 'YYYYMMDD');
                        END IF;             
                    WHEN g_period_semester THEN
                        IF SUBSTR(p_value, v_first_char, 1) = '1' THEN
                            v_output_value := to_date(v_year || '0101', 'YYYYMMDD') || g_time_separator || to_date(v_year || '0630', 'YYYYMMDD');
                        ELSE
                            v_output_value := to_date(v_year || '0701', 'YYYYMMDD') || g_time_separator || to_date(v_year || '1231', 'YYYYMMDD');
                        END IF;
                    WHEN g_period_year THEN
                            v_output_value := to_date(v_year || '0101', 'YYYYMMDD') || g_time_separator || to_date(v_year || '1231', 'YYYYMMDD');
                END CASE;                
        end case; 

        return v_output_value;
    EXCEPTION 
    WHEN INVALID_CAST THEN
         RAISE_APPLICATION_ERROR (-20000,'Invalid Cast');  
    WHEN OTHERS THEN
         RAISE_APPLICATION_ERROR (-20000,'Invalid Cast');      
    END cast_to_time;
    -------------------------------------------------------------------------------- 
    FUNCTION cast_to_date (p_value varchar2, p_datatype varchar2, p_mask varchar2) return DATE as 
        v_start VARCHAR2(10);
        v_period VARCHAR2(1);
        v_year VARCHAR2(4);
        v_first_char INTEGER;
        v_output_value DATE; 

    BEGIN 
        case p_datatype 
            when 'STRING' then 
            IF LENGTH(p_value) <> LENGTH(p_mask)
            THEN
               RAISE INVALID_CAST;
             END IF;
            --   
            v_output_value := TRUNC(TO_DATE ( p_value, p_mask)); 
            when 'TIME_PERIOD' then 
                v_start := nvl(p_mask, 'START');

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
                            --v_output_value := trunc(to_date(v_year || to_char(SUBSTR(p_value, v_first_char, 2) * 7, '000'), 'YYYYDDD'), 'IW');
                        	v_output_value := VTL_PKG.iso_week_to_date(v_year, SUBSTR(p_value, v_first_char, 2)) ;--trunc(to_date(v_year || to_char(SUBSTR(p_value, v_first_char, 2) * 7, '000'), 'YYYYDDD'), 'IW');
                        ELSE
                            v_output_value := VTL_PKG.iso_week_to_date(v_year, SUBSTR(p_value, v_first_char, 2)) + 6;--trunc(to_date(v_year || to_char(SUBSTR(p_value, v_first_char, 2) * 7, '000'), 'YYYYDDD'), 'IW') +6;
                        END IF;
                    WHEN g_period_month THEN
                        IF v_start = 'START' THEN
                            v_output_value := to_date(v_year || SUBSTR(p_value, v_first_char, 2) || '01', 'YYYYMMDD');
                        ELSE
                            v_output_value := last_day(to_date(v_year || SUBSTR(p_value, v_first_char, 2) || '01', 'YYYYMMDD'));
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
    EXCEPTION
    WHEN INVALID_CAST THEN
           RAISE_APPLICATION_ERROR (-20000, 'Invalid Cast');    
    END cast_to_date;
        -------------------------------------------------------------------------------- 
    FUNCTION cast_to_timeperiod (p_value DATE, p_datatype VARCHAR2, p_mask VARCHAR2) RETURN VARCHAR2 IS
    BEGIN
        return cast_to_timeperiod (to_char(p_value, p_mask), p_datatype, p_mask);
    END cast_to_timeperiod;  
    -------------------------------------------------------------------------------- 
    FUNCTION cast_to_timeperiod (p_value VARCHAR2, p_datatype VARCHAR2, p_mask VARCHAR2) RETURN VARCHAR2 IS
        v_retval STRING(50); 
        v_output_value STRING(100); 

    BEGIN
        case p_datatype 
            when 'DATE' then v_output_value :=  TO_CHAR(TO_DATE(p_value, p_mask), 'YYYY') || g_period_day || TO_CHAR(TO_DATE(p_value, p_mask), 'DDD');
            when 'STRING' then 
                v_retval := period_indicator (p_value);

                IF v_retval = 'Invalid format' THEN
                    v_output_value := 'Not castable value';
                ELSE
                    v_output_value :=  p_value;
                END IF;
        end case;

        return v_output_value;
    END cast_to_timeperiod; 
    -------------------------------------------------------------------------------- 
    FUNCTION cast_to_string  (p_value DATE, p_datatype VARCHAR2, p_mask VARCHAR2) RETURN VARCHAR2 IS
        v_output_value STRING(1000); 

    BEGIN
        case p_datatype 

            when 'DATE' then v_output_value  := to_char(p_value, p_mask);      
        end case; 

        return v_output_value;
    END cast_to_string;        
    -------------------------------------------------------------------------------- 
    FUNCTION cast_to_string  (p_value VARCHAR2, p_datatype VARCHAR2, p_mask VARCHAR2) RETURN VARCHAR2 IS
        v_position_mask INTEGER;
        v_left_mask VARCHAR2(50);
        v_right_mask VARCHAR2(50);
        v_position INTEGER;
        v_left_value VARCHAR2(50);
        v_righ_value VARCHAR2(50);
    	v_output_value STRING(1000); 

    BEGIN
        case p_datatype 
            when 'INTEGER' then v_output_value :=  p_value;
            when 'NUMBER' then v_output_value :=  p_value; 
            when 'BOOLEAN' then 
                            case when UPPER(p_value) IN (g_true_value, '1') 
                                then v_output_value := '1'; 
                                else v_output_value := '0'; 
                            end case;
            when 'TIME' then 
           				v_position_mask := INSTR(p_mask, g_time_separator);
           				v_left_mask := TRIM(SUBSTR(p_mask, 1, v_position_mask - 1));
           				v_right_mask := TRIM(SUBSTR(p_mask, v_position_mask + 1));
           			
           				v_position := INSTR(p_value, g_time_separator);
           				v_left_value := TRIM(SUBSTR(p_value, 1, v_position - 1));
                        v_righ_value := TRIM(SUBSTR(p_value, v_position + 1));
                       
                       v_output_value := to_char(to_date(v_left_value, v_left_mask), v_left_mask) || g_time_separator || to_char(to_date(v_righ_value, v_right_mask), v_right_mask);
            when 'DATE' then v_output_value  := to_date(p_value, p_mask);      
            when 'DURATION' then v_output_value := to_char(p_value);
            when 'TIME_PERIOD' then v_output_value := to_char(p_value);
        end case; 

        return v_output_value;
    END cast_to_string;    
    -------------------------------------------------------------------------------- 
    FUNCTION cast_to_duration (p_value VARCHAR2, p_datatype VARCHAR2, p_mask VARCHAR2) RETURN VARCHAR2 
    IS
    --
    V_LN_MASK    NUMBER;
    V_LN_VALUE   NUMBER;
    V_MASK       VARCHAR2(100);
    V_VALUE      VARCHAR2(100);
    V_VALUE_TMP  VARCHAR2(100);
    V_VALUE_N    NUMBER;
    V_P_mask     VARCHAR2(100);
    V_P_value    VARCHAR2(100);    
    V_CAST       VARCHAR2(2) := 'OK';
    --
    v_output_value VARCHAR2(1000); 
    --
    V_LOOP     NUMBER := 1;
    v_search_char VARCHAR2(1);
    v_count_period      NUMBER := 0;

 BEGIN
 --
 v_output_value := p_value;
 -- 
 v_value := p_value;
 v_value_tmp := p_value;
 v_mask := p_mask;
 --
 SELECT LENGTH(REPLACE (P_MASK, '\', ''))
 INTO V_LN_MASK FROM DUAL;
 
 IF  (V_LN_MASK <> LENGTH( P_VALUE)) or ( v_value NOT LIKE 'P%') OR (v_mask NOT LIKE '\P%')
 THEN
      V_CAST := 'KO';

 ELSE
      
         SELECT LENGTH(v_mask) INTO V_LN_MASK 
           FROM DUAL;
           
         WHILE  v_loop < V_LN_MASK
         LOOP    
                V_P_mask := V_P_mask||SUBSTR( v_mask, INSTR(v_mask, '\', v_loop) +1, 1);
                        
                IF v_p_mask is not null
                then
                 
                  v_loop := INSTR(v_mask, '\', v_loop) + 2;
            
                ELSE 
                   v_loop := V_LN_MASK +1 ; -- exit loop
                END IF;     
              
           END LOOP;
         --
          SELECT LENGTH(v_value) INTO V_LN_VALUE
           FROM DUAL;
         --
          SELECT LENGTH(v_p_mask) INTO v_count_period 
           FROM DUAL;
         --  
         v_loop := 1;
         --
           
          WHILE  v_loop <= v_count_period
          LOOP 
               --
                v_search_char:= SUBSTR(v_p_mask, v_loop, 1);   
                v_p_value := v_p_value||SUBSTR( v_value_tmp, INSTR(v_value_tmp, v_search_char) , 1);
                --              
                IF v_p_value is not null AND INSTR(v_value_tmp, v_search_char) > 0
                THEN
                 v_value_tmp :=  SUBSTR(v_value_tmp, INSTR(v_value_tmp, v_search_char));        
                 V_VALUE := REPLACE(v_value,v_search_char, '');  
               
                END IF;
             --   
             v_loop := v_loop + 1;
             --         
          END LOOP;
          --
          V_VALUE := REPLACE(v_value, 'T', '');
          --      
         -- 
         IF  V_P_mask = V_P_value
         THEN
             V_VALUE_N := TO_NUMBER(v_value);
         ELSE
             V_CAST := 'KO';       
         END IF;     
                       
 END IF;   

 IF V_CAST = 'KO'
 THEN 
     RAISE INVALID_CAST;
 END IF;  
   
 RETURN v_output_value;
        
EXCEPTION 
WHEN INVALID_CAST THEN
  RAISE_APPLICATION_ERROR (-20000,'Invalid Cast');
WHEN OTHERS THEN
  RAISE_APPLICATION_ERROR (-20000,'Invalid Number');
END;
   --------------------------------------------------------------------------------
    FUNCTION convert_to_date (p_timeval DATE) RETURN DATE IS
	BEGIN

        RETURN p_timeval;
	END convert_to_date; 
   --------------------------------------------------------------------------------
    FUNCTION convert_to_date (p_timeval TIMESTAMP) RETURN DATE IS
        v_retval DATE;
	BEGIN
		v_retval := trunc(p_timeval);

        RETURN v_retval;
	END convert_to_date;    
   --------------------------------------------------------------------------------
    FUNCTION convert_to_date (p_timeval VARCHAR2) RETURN DATE IS
        v_size INTEGER;
        v_position INTEGER;
        v_period VARCHAR2(1);
        v_retval DATE;
	BEGIN       
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
                    v_position := INSTR(p_timeval, g_time_separator);
                    
                    IF v_position <> 0 THEN
                        --YYYY-MM/YYYY-MM format
                        v_retval := to_date(substr(p_timeval, 1, v_position -1) || '-01', 'yyyy-mm-dd');
                    ELSE
                       v_retval := cast_to_date(p_timeval, 'TIME_PERIOD', 'START');
                    END IF;
                END IF;
        END CASE;
        
        RETURN v_retval;
	END convert_to_date;
    --------------------------------------------------------------------------------
    FUNCTION convert_from_date (p_timeval DATE, p_period VARCHAR2, date_value VARCHAR2) RETURN VARCHAR2 IS
        v_position INTEGER;
        v_retval VARCHAR2(50);
	BEGIN
        CASE p_period
            WHEN g_period_day THEN
                --YYYYDddd format
                v_retval := to_char(p_timeval, 'YYYY') || g_period_day || to_char(p_timeval, 'DDD');
            WHEN g_period_week THEN
                --YYYYWww format
                v_retval := to_char(p_timeval, 'YYYY') || g_period_week || to_char(p_timeval, 'WW');
            WHEN g_period_month THEN
                v_position := INSTR(date_value, g_time_separator);
                
                IF v_position <> 0 THEN
                    --YYYY-MM/YYYY-MM format
                    v_retval := to_char(p_timeval, 'YYYY') || '-' || SUBSTR(date_value, 6, 2) || g_time_separator || to_char(p_timeval, 'YYYY') || '-' || SUBSTR(date_value, 14, 2);
                ELSE
                    --YYYYMmm format
                    v_retval := to_char(p_timeval, 'YYYY') || g_period_month || to_char(p_timeval, 'MM');
                END IF; 
            WHEN g_period_quarter THEN
                --YYYYQq format
                IF to_char(p_timeval, 'MM') <= 3 THEN
                    v_retval := to_char(p_timeval, 'YYYY') || g_period_quarter || '1';
                ELSIF to_char(p_timeval, 'MM') <= 6 THEN
                    v_retval := to_char(p_timeval, 'YYYY') || g_period_quarter || '2';
                ELSIF to_char(p_timeval, 'MM') <= 9 THEN
                    v_retval := to_char(p_timeval, 'YYYY') || g_period_quarter || '3';
                ELSE
                    v_retval := to_char(p_timeval, 'YYYY') || g_period_quarter || '4';
                END IF; 
            WHEN g_period_semester THEN
                --YYYYSs format
                IF to_char(p_timeval, 'MM') <= 6 THEN
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
	END convert_from_date;
    --------------------------------------------------------------------------------
    -- Procedures
    --------------------------------------------------------------------------------
    PROCEDURE fill_time_series (p_input_dataset VARCHAR2, p_id_list VARCHAR2, p_id_time VARCHAR2, p_limits_method VARCHAR2) IS
    --- Variabili
    v_input_dataset  VARCHAR2 (1000) := p_input_dataset; -- � il nome del dataset di input inviato come parametro nella call della procedura
    v_id_list VARCHAR2 (1000) := p_id_list;  -- � la lista degli identificativi (tranne l'ID di tipo TIME) del dataset di input inviato come parametro nella call della procedura
    v_id_time VARCHAR2 (1000) := p_id_time; -- � l'ID di tipo TIME del dataset di input inviato come parametro nella call della procedura
    v_limits_method VARCHAR2 (1000) := p_limits_method; -- valorizzato con SINGLE o con ALL

    v_if_exists_cycle_tab int; -- variabile che sar� valorizzata con 0 se non esiste la tabella da droppare, con 1 se esiste. Utilizzo questa variabile per non far andare in errore il DROP nel caso in cui non esiste la tabella FILL_TIME_SERIES_CYCLE
    v_if_exists_dsr_tab int;  -- variabile che sar� valorizzata con 0 se non esiste la tabella da droppare, con 1 se esiste. Utilizzo questa variabile per non far andare in errore il DROP nel caso in cui non esiste la tabella FILL_TIME_SERIES_DSR

    v_min_all_time DATE; --variabile che indica la data minima nel caso di ALL
    v_max_all_time DATE; --variabile che indica la data massima nel caso di ALL
    v_min_time VARCHAR2(30); --variabile che utilizzer� nel ciclo di incremento dei time
    v_max_time VARCHAR2(30); --variabile che utilizzer� nel ciclo di incremento dei time
    v_period VARCHAR2(10); --variabile che utilizzer� nel ciclo di incremento dei time
    v_loop_time_1 VARCHAR2(100); --variabile che utilizzer� nel ciclo di incremento dei time
    v_loop_time_2 VARCHAR2(100); --variabile che utilizzer� nel ciclo di incremento dei time
    v_loop_row int := 1; -- per ciclare le righe della tabella "CURSORE". L'inizializzo a 1
    v_loop_row_end int;  -- per concludere il ciclo sulle righe della tabella "CURSORE"

    --- Scrivo in due variabili i nomi delle tabelle che crea la procedura. Utilizzo le variabili perch� sono pi� comode negli EXECUTE IMMEDIATE
    v_cycle_tab_name VARCHAR2 (50) :=  'TEMPORARY_FTS_CYCLE';  --- tabella che utilizzo come cursore per ciclare 
    v_dsr_tab_name VARCHAR2 (50) :=  'TEMPORARY_FTS';  --- tabella dove scrivo il DATASET RISULTATO

    --- Sql statement per costruire il "CURSORE" in caso di v_limits_method='SINGLE'
    v_sql_stmt_single VARCHAR2 (2000) :=
    'CREATE TABLE '||v_cycle_tab_name||' AS 
    SELECT DISTINCT '
                 ||v_id_list||', '||
                 'VTL_PKG.PERIOD_INDICATOR('||v_id_time||') AS PERIOD, 
                 MAX('||v_id_time||') OVER (PARTITION BY '||v_id_list||', VTL_PKG.PERIOD_INDICATOR('||v_id_time||')) AS MAX_TIME, 
                 MIN('||v_id_time||') OVER (PARTITION BY '||v_id_list||',  VTL_PKG.PERIOD_INDICATOR('||v_id_time||')) AS MIN_TIME,
                 DENSE_RANK() OVER (ORDER BY '||v_id_list||', VTL_PKG.PERIOD_INDICATOR('||v_id_time||')) AS N_ROW
    FROM '||v_input_dataset||
    ' ORDER BY ' ||v_id_list||
                      ' , VTL_PKG.PERIOD_INDICATOR('||v_id_time||')';

    --- Sql statement per costruire il "CURSORE" in caso di v_limits_method='ALL'
    v_sql_stmt_all VARCHAR2 (2000) :=
    'CREATE TABLE '||v_cycle_tab_name||' AS 
    SELECT DISTINCT '
                 ||v_id_list||', '||
                 'VTL_PKG.PERIOD_INDICATOR('||v_id_time||') AS PERIOD, 
                 MIN('||v_id_time||') OVER (PARTITION BY '||v_id_list||',  VTL_PKG.PERIOD_INDICATOR('||v_id_time||')) AS MIN_TIME,
                 DENSE_RANK() OVER (ORDER BY '||v_id_list||', VTL_PKG.PERIOD_INDICATOR('||v_id_time||')) AS N_ROW
    FROM '||v_input_dataset||
    ' ORDER BY ' ||v_id_list||
                      ' , VTL_PKG.PERIOD_INDICATOR('||v_id_time||')';

    --- Sql statement per popolare la variabile v_loop_row_end contando le righe della tabella FILL_TIME_SERIES_CYCLE
    v_stmt_loop_row_end  VARCHAR2 (2000) := 'SELECT COUNT(*) FROM '||v_cycle_tab_name;

    --- Sql statement per creare la tabella DSR sulla base degli identificativi del DS di input
    v_stmt_create_dsr  VARCHAR2 (2000) := 
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
            
            --- popolo la variabile v_loop_row_end contando le righe della tabella FILL_TIME_SERIES_CYCLE. La variabile mi fornir� la condizione di uscita dal ciclo sul "CURSORE"
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
    
                v_loop_time_1 := SV_VTL_SQL.VTL_PKG.TIMESHIFT(v_min_time, -1); 
                v_loop_time_2 := SV_VTL_SQL.VTL_PKG.TIMESHIFT(v_min_time, -1);
    
                WHILE v_loop_time_2 <> v_max_time 
                LOOP
                    v_loop_time_2 := SV_VTL_SQL.VTL_PKG.TIMESHIFT(v_loop_time_1, 1);
        
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
        ELSE
            EXECUTE IMMEDIATE 'SELECT MIN(VTL_PKG.convert_to_date('||v_id_time||')) FROM '||v_input_dataset
            INTO v_min_all_time;

            EXECUTE IMMEDIATE 'SELECT MAX(VTL_PKG.convert_to_date('||v_id_time||')) FROM '||v_input_dataset
            INTO v_max_all_time;
        
            EXECUTE IMMEDIATE(v_sql_stmt_all);

            --- popolo la variabile v_loop_row_end contando le righe della tabella FILL_TIME_SERIES_CYCLE. La variabile mi fornir� la condizione di uscita dal ciclo sul "CURSORE"
            EXECUTE IMMEDIATE (v_stmt_loop_row_end)
            INTO v_loop_row_end;
            
            -- faccio ciclare le righe del "CURSORE"   
            WHILE v_loop_row <= v_loop_row_end
            LOOP
                EXECUTE IMMEDIATE 'SELECT MIN_TIME FROM '||v_cycle_tab_name||' WHERE N_ROW = :bind_looprow'
                INTO v_min_time
                USING v_loop_row;
                
                EXECUTE IMMEDIATE 'SELECT PERIOD FROM '||v_cycle_tab_name||' WHERE N_ROW = :bind_looprow'   
                INTO v_period 
                USING v_loop_row;

                --IF v_period = g_period_date_time THEN
                    v_min_time := convert_from_date(v_min_all_time, v_period, v_min_time);

                    v_max_time := convert_from_date(v_max_all_time, v_period, v_min_time);                   
                --ELSE
                --    v_min_time := convert_from_date(v_min_all_time, v_period, null);

                --    v_max_time := convert_from_date(v_max_all_time, v_period, null);
                --END IF;

                v_loop_time_1 := TIMESHIFT(v_min_time, -1); 
                v_loop_time_2 := TIMESHIFT(v_min_time, -1);
    
                WHILE (v_loop_time_2 <> v_max_time and v_loop_time_2 < v_max_time)
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
        END IF;

        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                NULL;
            WHEN OTHERS THEN
                -- Consider logging the error and then re-raise
                RAISE;
    END fill_time_series;

   END;
/
