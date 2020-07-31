DELIMITER |||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_and_bool`(p_left VARCHAR(5),p_right VARCHAR(5)) RETURNS varchar(5) CHARSET utf8
    DETERMINISTIC
BEGIN
	DECLARE g_true_value VARCHAR(4);
	DECLARE g_false_value VARCHAR(5);
	DECLARE v_left_bool BOOLEAN;
	DECLARE v_right_bool BOOLEAN;
	DECLARE v_res_bool BOOLEAN;
	DECLARE v_retval VARCHAR(10);
	SET g_true_value = 'TRUE';
	SET g_false_value = 'FALSE';


	IF p_left IS NULL 
	THEN
		SET v_left_bool = null;
	ELSE
		SET v_left_bool = case when p_left = g_true_value then true else false end;
	END IF;

	IF p_right IS null
		THEN
			SET v_right_bool = null;
		ELSE
			SET v_right_bool = case when p_right = g_true_value then true else false end;
		END IF;

        #apply AND operator to boolean values
        SET v_res_bool = v_left_bool AND v_right_bool;

        IF v_res_bool IS NULL 
        THEN
            SET v_retval = null;
        ELSE
            SET v_retval = case when v_res_bool 
           THEN g_true_value else g_false_value end;
        END IF;

        RETURN v_retval;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_is_number`(p_string VARCHAR(10)) RETURNS tinyint(1)
    DETERMINISTIC
BEGIN
	DECLARE res INT1;
	IF p_string REGEXP '^(-|\\+){0,1}([0-9]+\\.[0-9]*|[0-9]*\\.[0-9]+|[0-9]+)$' THEN
		SET res = 1;
	ELSE
		SET res = 0;
	END IF;
	RETURN res;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_xor_bool`(p_left VARCHAR(5),p_right VARCHAR(5)) RETURNS varchar(5) CHARSET utf8
    DETERMINISTIC
BEGIN
	DECLARE g_true_value VARCHAR(4);
	DECLARE g_false_value VARCHAR(5);
	DECLARE v_left_bool BOOLEAN;
	DECLARE v_right_bool BOOLEAN;
	DECLARE v_res_bool BOOLEAN;
	DECLARE v_retval VARCHAR(10);
	SET g_true_value = 'TRUE';
	SET g_false_value = 'FALSE';


	IF p_left IS NULL OR p_right IS NULL 
		THEN
			SET v_retval = null;
		ELSE
        IF p_left = p_right 
        	THEN
               SET v_retval = g_false_value;
            ELSE
               SET v_retval = g_true_value;
        END IF;
	END IF;

    RETURN v_retval;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_or_bool`(p_left VARCHAR(5),p_right VARCHAR(5)) RETURNS varchar(5) CHARSET utf8
    DETERMINISTIC
BEGIN
	DECLARE g_true_value VARCHAR(4);
	DECLARE g_false_value VARCHAR(5);
	DECLARE v_left_bool BOOLEAN;
	DECLARE v_right_bool BOOLEAN;
	DECLARE v_res_bool BOOLEAN;
	DECLARE v_retval VARCHAR(10);
	SET g_true_value = 'TRUE';
	SET g_false_value = 'FALSE';


	IF p_left IS NULL 
	THEN
		SET v_left_bool = null;
	ELSE
		SET v_left_bool = case when p_left = g_true_value then true else false end;
	END IF;

	IF p_right IS null
		THEN
			SET v_right_bool = null;
		ELSE
			SET v_right_bool = case when p_right = g_true_value then true else false end;
		END IF;

        #apply OR operator to boolean values
        SET v_res_bool = v_left_bool OR v_right_bool;

        IF v_res_bool IS NULL 
        THEN
            SET v_retval = null;
        ELSE
            SET v_retval = case when v_res_bool 
           THEN g_true_value else g_false_value end;
        END IF;

        RETURN v_retval;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_not_bool`(p_param VARCHAR(5)) RETURNS varchar(5) CHARSET utf8
    DETERMINISTIC
BEGIN
	DECLARE g_true_value VARCHAR(4);
	DECLARE g_false_value VARCHAR(5);
	DECLARE v_retval VARCHAR(10);
	SET g_true_value = 'TRUE';
	SET g_false_value = 'FALSE';

	IF p_param IS NULL 
		THEN
			SET v_retval = null;
	ELSE
        IF p_param = g_true_value 
        	THEN
                SET v_retval = g_false_value;
            ELSE
                SET v_retval = g_true_value;
        END IF;
	END IF;

    RETURN v_retval;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_period_indicator`(p_timeval VARCHAR(50)) RETURNS varchar(50) CHARSET utf8
    DETERMINISTIC
BEGIN
DECLARE g_time_separator VARCHAR(1);
DECLARE g_period_day VARCHAR(1);
DECLARE g_period_week VARCHAR(1);
DECLARE g_period_month VARCHAR(1);
DECLARE g_period_quarter VARCHAR(1);
DECLARE g_period_semester VARCHAR(1);
DECLARE g_period_year VARCHAR(1);
DECLARE g_period_date_time VARCHAR(1);
DECLARE v_size INT4;
DECLARE v_period VARCHAR(1);
DECLARE v_retval VARCHAR(50);
SET g_time_separator = '/';
SET g_period_day = 'D';
SET g_period_week = 'W';
SET g_period_month = 'M';
SET g_period_quarter = 'Q';
SET g_period_semester = 'S';
SET g_period_year = 'A';
SET g_period_date_time = 'T';

#SDMX v2.1 Time Period format
#yyyy-Qq (2014-Q1, 2014-Q2, 2014-Q3, 2014-Q4)
#yyyy-mm (2014-01, 2014-02, 2014-03, 2014-04, 2014-05, 2014-06, 2014-07, 2014-08, 2014-09, 2014-10, 2014-11, 2014-12)
#yyyy-Mmm (2014-M01,2014-M02,2014-M03, 2014-M04,2014-M05,2014-M06)
#other supported format
#like SDMX without '-'

SET v_size = LENGTH(p_timeval);

CASE v_size
	WHEN 4 THEN
    #date YYYY format
    	SET v_retval = g_period_year;
    WHEN 10 THEN
    	SET v_retval = g_period_date_time;
    ELSE 
        IF v_size < 5 THEN
        	SET v_retval = 'Invalid format';
        ELSE
            SET v_period = SUBSTRING(p_timeval, 5, 1);

        IF v_period = '-' THEN
        #SDMX v2.1 Time Period format
        	SET v_period = SUBSTRING(p_timeval, 6, 1);

            IF vtl_is_number(v_period) THEN
            	SET v_retval = g_period_month; 
            ELSE
            	IF v_period = g_period_day or v_period = g_period_week 
                	OR v_period = g_period_month or v_period = g_period_quarter 
                    OR v_period = g_period_semester or v_period = g_period_year THEN
                            SET v_retval = v_period; 
                ELSE
                	SET v_retval = 'Invalid format';
                END IF;
            END IF;
		ELSE
        #other supported format
        	IF vtl_is_number(v_period) THEN
            	SET v_retval = g_period_month; 
            ELSE
            	IF v_period = g_period_day or v_period = g_period_week 
                	OR v_period = g_period_month or v_period = g_period_quarter 
                    OR v_period = g_period_semester or v_period = g_period_year THEN
                            SET v_retval = v_period; 
                ELSE
                	SET v_retval = 'Invalid format';
                END IF;
            END IF;
		END IF;
	END IF;    
END CASE;

RETURN v_retval;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_timeshiftd`(p_timeval DATE, p_shiftval INTEGER) RETURNS DATE
    DETERMINISTIC
BEGIN
	DECLARE v_retval DATE;
	SET v_retval = DATE_ADD(p_timeval,INTERVAL p_shiftval YEAR);
	RETURN v_retval;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_timeshiftt`(p_timeval TIMESTAMP, p_shiftval INTEGER) RETURNS timestamp
    DETERMINISTIC
BEGIN
	DECLARE v_retval TIMESTAMP;
	SET v_retval = DATE_ADD(p_timeval,INTERVAL p_shiftval YEAR);
	RETURN v_retval;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_iso_week_to_date`(p_iso_year INT4,p_iso_week INT4) RETURNS date
    DETERMINISTIC
BEGIN
	DECLARE v_jan4_of_iso_year DATE;
	DECLARE v_first_day_of_iso_year DATE;
	DECLARE v_iso_date DATE;
	DECLARE v_iso_date_iso_year INTEGER;
	
	SET v_jan4_of_iso_year = STR_TO_DATE(CONCAT(p_iso_year, '-01-01'), '%Y-%m-%d');
    
	SET v_first_day_of_iso_year = case when (WEEKDAY(v_jan4_of_iso_year)+1) <= 4 then
   		date_add(v_jan4_of_iso_year, INTERVAL 1 - (WEEKDAY(v_jan4_of_iso_year)+1) DAY) else
   		date_add(v_jan4_of_iso_year, INTERVAL 8 - (WEEKDAY(v_jan4_of_iso_year)+1) DAY) end;
    
  
	SET v_iso_date = date_add(v_first_day_of_iso_year, INTERVAL 7 * (p_iso_week - 1) DAY);

    -- SET v_iso_date_iso_year = DATE_FORMAT(v_iso_date, '%Y');
   
    RETURN v_iso_date;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_timeshift`(p_timeval VARCHAR(50), p_shiftval INTEGER) RETURNS varchar(50) CHARSET utf8
    DETERMINISTIC
BEGIN
DECLARE g_time_separator VARCHAR(1);
DECLARE g_period_day VARCHAR(1);
DECLARE g_period_week VARCHAR(1);
DECLARE g_period_month VARCHAR(1);
DECLARE g_period_quarter VARCHAR(1);
DECLARE g_period_semester VARCHAR(1);
DECLARE g_period_year VARCHAR(1);
DECLARE v_size INT4;
DECLARE v_position INTEGER;
DECLARE v_period VARCHAR(1);
DECLARE v_leftval VARCHAR(50);
DECLARE v_rightval VARCHAR(50);
DECLARE v_retval VARCHAR(50);
SET g_time_separator = '/';
SET g_period_day = 'D';
SET g_period_week = 'W';
SET g_period_month = 'M';
SET g_period_quarter = 'Q';
SET g_period_semester = 'S';
SET g_period_year = 'A';

SET v_size = LENGTH(p_timeval);

        CASE v_size
          WHEN 4 THEN
            #date YYYY format
            SET v_retval = p_timeval + p_shiftval;
          WHEN 10 THEN
            #date YYYY-MM-DD format
            SET v_retval = DATE_FORMAT(vtl_timeshiftd(STR_TO_DATE(p_timeval, '%Y-%m-%d'), p_shiftval), '%Y-%m-%d'); #****
          ELSE 
            IF v_size < 5 THEN
                SET v_retval = 'Invalid format';
            ELSE
                SET v_position = INSTR(p_timeval, g_time_separator);
                IF v_position <> 0 THEN
                    #In this case the increase is annual and not monthly
                    SET v_leftval = vtl_timeshiftRecursive(SUBSTRING(p_timeval, 1, v_position - 1), p_shiftval * 12);
                    SET v_rightval = vtl_timeshiftRecursive(SUBSTRING(p_timeval, v_position + 1), p_shiftval * 12);
                    SET v_retval = CONCAT(v_leftval, g_time_separator, v_rightval);
                ELSE
                    SET v_period = SUBSTRING(p_timeval, 5, 1);

                    IF v_period = '-' THEN
                        SET v_period = SUBSTRING(p_timeval, 6, 1);

                        CASE v_period
                            WHEN g_period_day THEN
                                #date YYYY-Dddd format OK
                                SET v_retval = DATE_FORMAT(date_add(STR_TO_DATE(CONCAT(SUBSTRING(p_timeval, 1, 4), SUBSTRING(p_timeval, 7, 3)), '%Y%j'),INTERVAL p_shiftval DAY), '%Y%j');
                                SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4), '-', g_period_day, SUBSTRING(v_retval, 5, 3));
                            WHEN g_period_week THEN
                                #date YYYY-Www format OK
                                SET v_retval = DATE_FORMAT(date_add(vtl_iso_week_to_date(SUBSTRING(p_timeval, 1, 4), SUBSTRING(p_timeval, 7, 2)), INTERVAL (p_shiftval * 7) DAY), '%x%v');
                                SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4), '-', g_period_week, SUBSTRING(v_retval, 5, 2));
                            WHEN g_period_month THEN
                                #period YYYY-Mmm format OK
                                SET v_retval = DATE_FORMAT(date_add(CAST(CONCAT(SUBSTRING(p_timeval, 1, 4), SUBSTRING(p_timeval, 7, 2), '01') as date), INTERVAL p_shiftval MONTH), '%Y%m');
                                SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4) , '-' , g_period_month , SUBSTRING(v_retval, 5, 2));
                            WHEN g_period_quarter THEN
                                #date YYYY-Qq format NON FUNZIONA
                                SET v_retval = DATE_FORMAT(date_add(CAST(CONCAT(SUBSTRING(p_timeval, 1, 4), LPAD(SUBSTRING(p_timeval, 7, 1)* 3, 2, '0'), '01') as date),INTERVAL p_shiftval * 3 MONTH), '%Y%m');
                                SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4), '-', g_period_quarter, SUBSTRING(v_retval, 5, 2) /3);
                            WHEN g_period_semester THEN
                                #date YYYY-Ss format
                                SET v_retval = DATE_FORMAT(date_add(CAST(CONCAT(SUBSTRING(p_timeval, 1, 4), LPAD(SUBSTRING(p_timeval, 7, 1)* 6, 2, '0'), '01') as date), INTERVAL p_shiftval * 6 MONTH), '%Y%m');
                                SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4), '-', g_period_semester, SUBSTRING(v_retval, 5, 2) /6);
                            WHEN g_period_year THEN
                                #period YYYY-A format OK
                                SET v_retval = CONCAT(SUBSTRING(p_timeval, 1, 4) + p_shiftval , '-' , g_period_year);
                            ELSE
                            	IF v_size = 7 THEN
                                    #date YYYY-mm format OK
                                    SET v_retval = DATE_FORMAT(date_add(CAST(CONCAT(p_timeval, '-01') as date),INTERVAL p_shiftval MONTH), '%Y-%m');
                                ELSE
                                    IF v_size = 8 THEN
                                        #date YYYY-xMM format
                                        SET v_retval = DATE_FORMAT(date_add(CAST(CONCAT(SUBSTRING(p_timeval, 1, 4), SUBSTRING(p_timeval, 7, 2),'01') as date),INTERVAL p_shiftval MONTH), '%Y%m');
                                        SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4), SUBSTRING(p_timeval, 5, 2), SUBSTRING(v_retval, 5, 2)); 
                                    ELSE
                                        SET v_retval = 'Invalid format';
                                    END IF;
                                END IF;
                        END CASE;
                    ELSE
                        CASE v_period
                            WHEN g_period_day THEN
                                #date YYYYDddd format OK
                                SET v_retval = DATE_FORMAT(date_add(STR_TO_DATE(CONCAT(SUBSTRING(p_timeval, 1, 4), SUBSTRING(p_timeval, 6, 3)), '%Y%j'),INTERVAL p_shiftval DAY), '%Y%j');
                                SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4), g_period_day, SUBSTRING(v_retval, 5, 3));
                            WHEN g_period_week THEN
                                #date YYYYWww format OK
                                SET v_retval = DATE_FORMAT(date_add(vtl_iso_week_to_date(SUBSTRING(p_timeval, 1, 4), SUBSTRING(p_timeval, 6, 2)), INTERVAL (p_shiftval * 7) DAY), '%x%v');
                                SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4), g_period_week, SUBSTRING(v_retval, 5, 2));
                            WHEN g_period_month THEN
                                #period YYYYMmm format OK
                                SET v_retval = DATE_FORMAT(date_add(CAST(CONCAT(SUBSTRING(p_timeval, 1, 4), SUBSTRING(p_timeval, 6, 2), '01') as date), INTERVAL p_shiftval MONTH), '%Y%m');
                                SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4) , g_period_month , SUBSTRING(v_retval, 5, 2));
                            WHEN g_period_quarter THEN
                                #date YYYYQq format 
                                SET v_retval = DATE_FORMAT(date_add(CAST(CONCAT(SUBSTRING(p_timeval, 1, 4), LPAD(SUBSTRING(p_timeval, 6, 1)* 3, 2, '0'), '01') as date),INTERVAL p_shiftval * 3 MONTH), '%Y%m');
                                SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4), g_period_quarter, SUBSTRING(v_retval, 5, 2) /3);
                            WHEN g_period_semester THEN
                                #date YYYYSs format
                                SET v_retval = DATE_FORMAT(date_add(CAST(CONCAT(SUBSTRING(p_timeval, 1, 4), LPAD(SUBSTRING(p_timeval, 6, 1)* 6, 2, '0'), '01') as date), INTERVAL p_shiftval * 6 MONTH), '%Y%m');
                                SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4), g_period_semester, SUBSTRING(v_retval, 5, 2) /6);
                            WHEN g_period_year THEN
                                #period YYYYA format OK
                                SET v_retval = CONCAT(SUBSTRING(p_timeval, 1, 4) + p_shiftval , g_period_year);
                            ELSE 
                                IF v_size = 6 THEN
                                    #date YYYYMM format OK
                                    SET v_retval = DATE_FORMAT(date_add(CAST(CONCAT(p_timeval, '01') as date),INTERVAL p_shiftval MONTH), '%Y%m');
                                ELSE
                                    IF v_size = 7 THEN
                                        #date YYYYxMM format OK
                                        SET v_retval = DATE_FORMAT(date_add(CAST(CONCAT(SUBSTRING(p_timeval, 1, 4), SUBSTRING(p_timeval, 6, 2),'01') as date),INTERVAL p_shiftval MONTH), '%Y%m');
                                        SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4), SUBSTRING(p_timeval, 5, 1), SUBSTRING(v_retval, 5, 2)); 
                                    ELSE
                                        SET v_retval = 'Invalid format';
                                    END IF; 
                                END IF;   
                        END CASE;
                    END IF;   
                END IF;    
            END IF;
        END CASE;

        RETURN v_retval;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_timeshiftRecursive`(p_timeval VARCHAR(50), p_shiftval INTEGER) RETURNS varchar(50) CHARSET utf8
    DETERMINISTIC
BEGIN
DECLARE g_time_separator VARCHAR(1);
DECLARE g_period_day VARCHAR(1);
DECLARE g_period_week VARCHAR(1);
DECLARE g_period_month VARCHAR(1);
DECLARE g_period_quarter VARCHAR(1);
DECLARE g_period_semester VARCHAR(1);
DECLARE g_period_year VARCHAR(1);
DECLARE v_size INT4;
DECLARE v_position INTEGER;
DECLARE v_period VARCHAR(1);
DECLARE v_leftval VARCHAR(50);
DECLARE v_rightval VARCHAR(50);
DECLARE v_retval VARCHAR(50);
SET g_time_separator = '/';
SET g_period_day = 'D';
SET g_period_week = 'W';
SET g_period_month = 'M';
SET g_period_quarter = 'Q';
SET g_period_semester = 'S';
SET g_period_year = 'A';

SET v_size = LENGTH(p_timeval);

        CASE v_size
          WHEN 4 THEN
            #date YYYY format
            SET v_retval = p_timeval + p_shiftval;
          WHEN 10 THEN
            #date YYYY-MM-DD format
            SET v_retval = DATE_FORMAT(vtl_timeshiftd(STR_TO_DATE(p_timeval, '%Y-%m-%d'), p_shiftval), '%Y-%m-%d'); #****
          ELSE 
            IF v_size < 5 THEN
                SET v_retval = 'Invalid format';
            ELSE
                SET v_position = INSTR(p_timeval, g_time_separator);
                IF v_position <> 0 THEN
                    SET v_retval = 'Invalid format';
                ELSE
                    SET v_period = SUBSTRING(p_timeval, 5, 1);

                    IF v_period = '-' THEN
                        SET v_period = SUBSTRING(p_timeval, 6, 1);

                        CASE v_period
                            WHEN g_period_day THEN
                                #date YYYY-Dddd format OK
                                SET v_retval = DATE_FORMAT(date_add(STR_TO_DATE(CONCAT(SUBSTRING(p_timeval, 1, 4), SUBSTRING(p_timeval, 7, 3)), '%Y%j'),INTERVAL p_shiftval DAY), '%Y%j');
                                SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4), '-', g_period_day, SUBSTRING(v_retval, 5, 3));
                            WHEN g_period_week THEN
                                #date YYYY-Www format OK
                                SET v_retval = DATE_FORMAT(date_add(vtl_iso_week_to_date(SUBSTRING(p_timeval, 1, 4), SUBSTRING(p_timeval, 7, 2)), INTERVAL (p_shiftval * 7) DAY), '%x%v');
                                SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4), '-', g_period_week, SUBSTRING(v_retval, 5, 2));
                            WHEN g_period_month THEN
                                #period YYYY-Mmm format OK
                                SET v_retval = DATE_FORMAT(date_add(CAST(CONCAT(SUBSTRING(p_timeval, 1, 4), SUBSTRING(p_timeval, 7, 2), '01') as date), INTERVAL p_shiftval MONTH), '%Y%m');
                                SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4) , '-' , g_period_month , SUBSTRING(v_retval, 5, 2));
                            WHEN g_period_quarter THEN
                                #date YYYY-Qq format NON FUNZIONA
                                SET v_retval = DATE_FORMAT(date_add(CAST(CONCAT(SUBSTRING(p_timeval, 1, 4), LPAD(SUBSTRING(p_timeval, 7, 1)* 3, 2, '0'), '01') as date),INTERVAL p_shiftval * 3 MONTH), '%Y%m');
                                SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4), '-', g_period_quarter, SUBSTRING(v_retval, 5, 2) /3);
                            WHEN g_period_semester THEN
                                #date YYYY-Ss format
                                SET v_retval = DATE_FORMAT(date_add(CAST(CONCAT(SUBSTRING(p_timeval, 1, 4), LPAD(SUBSTRING(p_timeval, 7, 1)* 6, 2, '0'), '01') as date), INTERVAL p_shiftval * 6 MONTH), '%Y%m');
                                SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4), '-', g_period_semester, SUBSTRING(v_retval, 5, 2) /6);
                            WHEN g_period_year THEN
                                #period YYYY-A format OK
                                SET v_retval = CONCAT(SUBSTRING(p_timeval, 1, 4) + p_shiftval , '-' , g_period_year);
                            ELSE
                            	IF v_size = 7 THEN
                                    #date YYYY-mm format OK
                                    SET v_retval = DATE_FORMAT(date_add(CAST(CONCAT(p_timeval, '-01') as date),INTERVAL p_shiftval MONTH), '%Y-%m');
                                ELSE
                                    IF v_size = 8 THEN
                                        #date YYYY-xMM format
                                        SET v_retval = DATE_FORMAT(date_add(CAST(CONCAT(SUBSTRING(p_timeval, 1, 4), SUBSTRING(p_timeval, 7, 2),'01') as date),INTERVAL p_shiftval MONTH), '%Y%m');
                                        SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4), SUBSTRING(p_timeval, 5, 2), SUBSTRING(v_retval, 5, 2)); 
                                    ELSE
                                        SET v_retval = 'Invalid format';
                                    END IF;
                                END IF;
                        END CASE;
                    ELSE
                        CASE v_period
                            WHEN g_period_day THEN
                                #date YYYYDddd format OK
                                SET v_retval = DATE_FORMAT(date_add(STR_TO_DATE(CONCAT(SUBSTRING(p_timeval, 1, 4), SUBSTRING(p_timeval, 6, 3)), '%Y%j'),INTERVAL p_shiftval DAY), '%Y%j');
                                SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4), g_period_day, SUBSTRING(v_retval, 5, 3));
                            WHEN g_period_week THEN
                                #date YYYYWww format OK
                                SET v_retval = DATE_FORMAT(date_add(vtl_iso_week_to_date(SUBSTRING(p_timeval, 1, 4), SUBSTRING(p_timeval, 6, 2)), INTERVAL (p_shiftval * 7) DAY), '%x%v');
                                SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4), g_period_week, SUBSTRING(v_retval, 5, 2));
                            WHEN g_period_month THEN
                                #period YYYYMmm format OK
                                SET v_retval = DATE_FORMAT(date_add(CAST(CONCAT(SUBSTRING(p_timeval, 1, 4), SUBSTRING(p_timeval, 6, 2), '01') as date), INTERVAL p_shiftval MONTH), '%Y%m');
                                SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4) , g_period_month , SUBSTRING(v_retval, 5, 2));
                            WHEN g_period_quarter THEN
                                #date YYYYQq format 
                                SET v_retval = DATE_FORMAT(date_add(CAST(CONCAT(SUBSTRING(p_timeval, 1, 4), LPAD(SUBSTRING(p_timeval, 6, 1)* 3, 2, '0'), '01') as date),INTERVAL p_shiftval * 3 MONTH), '%Y%m');
                                SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4), g_period_quarter, SUBSTRING(v_retval, 5, 2) /3);
                            WHEN g_period_semester THEN
                                #date YYYYSs format
                                SET v_retval = DATE_FORMAT(date_add(CAST(CONCAT(SUBSTRING(p_timeval, 1, 4), LPAD(SUBSTRING(p_timeval, 6, 1)* 6, 2, '0'), '01') as date), INTERVAL p_shiftval * 6 MONTH), '%Y%m');
                                SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4), g_period_semester, SUBSTRING(v_retval, 5, 2) /6);
                            WHEN g_period_year THEN
                                #period YYYYA format OK
                                SET v_retval = CONCAT(SUBSTRING(p_timeval, 1, 4) + p_shiftval , g_period_year);
                            ELSE 
                                IF v_size = 6 THEN
                                    #date YYYYMM format OK
                                    SET v_retval = DATE_FORMAT(date_add(CAST(CONCAT(p_timeval, '01') as date),INTERVAL p_shiftval MONTH), '%Y%m');
                                ELSE
                                    IF v_size = 7 THEN
                                        #date YYYYxMM format OK
                                        SET v_retval = DATE_FORMAT(date_add(CAST(CONCAT(SUBSTRING(p_timeval, 1, 4), SUBSTRING(p_timeval, 6, 2),'01') as date),INTERVAL p_shiftval MONTH), '%Y%m');
                                        SET v_retval = CONCAT(SUBSTRING(v_retval, 1, 4), SUBSTRING(p_timeval, 5, 1), SUBSTRING(v_retval, 5, 2)); 
                                    ELSE
                                        SET v_retval = 'Invalid format';
                                    END IF; 
                                END IF;   
                        END CASE;
                    END IF;   
                END IF;    
            END IF;
        END CASE;

        RETURN v_retval;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_time_aggv2`(p_timeval DATE, p_period_to VARCHAR(50), p_period_from VARCHAR(50), p_start VARCHAR(50)) RETURNS datetime
    DETERMINISTIC
BEGIN
	DECLARE v_retval DATE;
	SET v_retval = STR_TO_DATE(vtl_time_agg(DATE_FORMAT(p_timeval, '%Y-%m-%d'), p_period_to, p_period_from, p_start), '%Y-%m-%d');
	RETURN v_retval;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_time_agg`(p_timeval VARCHAR(50), p_period_to VARCHAR(50), p_period_from VARCHAR(50), p_start VARCHAR(50)) RETURNS varchar(50) CHARSET utf8
    DETERMINISTIC
BEGIN
DECLARE g_true_value VARCHAR(4);
DECLARE g_false_value VARCHAR(5);
DECLARE g_time_separator VARCHAR(1);
DECLARE g_period_day VARCHAR(1);
DECLARE g_period_week VARCHAR(1);
DECLARE g_period_month VARCHAR(1);
DECLARE g_period_quarter VARCHAR(1);
DECLARE g_period_semester VARCHAR(1);
DECLARE g_period_year VARCHAR(1);
DECLARE v_size INTEGER;
DECLARE v_position INTEGER;
DECLARE v_period VARCHAR(1);
DECLARE v_start VARCHAR(10);
DECLARE v_retval VARCHAR(50);

SET g_true_value = 'TRUE';
SET g_false_value = 'FALSE';
SET g_time_separator = '/';
SET g_period_day = 'D';
SET g_period_week = 'W';
SET g_period_month = 'M';
SET g_period_quarter = 'Q';
SET g_period_semester = 'S';
SET g_period_year = 'A';


IF p_timeval = 'NULL' THEN
	return null;
END IF; 
        
SET v_start = coalesce(p_start, 'first');
        
SET v_size = LENGTH(p_timeval);

CASE v_size
	WHEN 4 THEN
    #date YYYY format
    	SET v_retval = p_timeval;
	WHEN 10 THEN
    #date YYYY-%m-DD format
    	CASE p_period_to
        	WHEN g_period_day THEN
            	SET v_retval = p_timeval;
        	WHEN g_period_week THEN
            	IF v_start = 'first' THEN
            		SET v_retval = DATE_FORMAT(STR_TO_DATE(CONCAT(YEARWEEK(p_timeval, 5), ' Monday'), '%X%V %W'), '%Y-%m-%d');
            	ELSE
                	SET v_retval =  DATE_FORMAT(date_add(STR_TO_DATE(CONCAT(YEARWEEK(p_timeval, 5), ' Monday'), '%X%V %W'), INTERVAL 6 DAY), '%Y-%m-%d');
                END IF;
        	WHEN g_period_month THEN
            	IF v_start = 'first' THEN
            		SET v_retval = CONCAT(DATE_FORMAT(last_day(CAST(p_timeval as date)), '%Y-%m'), '-01');
            	ELSE
                	SET v_retval = DATE_FORMAT(last_day(CAST(p_timeval as date)), '%Y-%m-%d');
                END IF;
            WHEN g_period_quarter THEN
            	IF SUBSTR(p_timeval, 6, 2) <= 3 THEN
                	IF v_start = 'first' THEN
                    	SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), '-01-01');
                	ELSE
                    	SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), '-03-31');
                    END IF;
            	ELSEIF SUBSTR(p_timeval, 6, 2) <= 6 THEN
                	IF v_start = 'first' THEN
                    	SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), '-04-01');
                	ELSE
                    	SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), '-06-30');
                    END IF;
            	ELSEIF SUBSTR(p_timeval, 6, 2) <= 9 THEN
                	IF v_start = 'first' THEN
                    	SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), '-07-01');
                	ELSE
                    	SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), '-09-30');
                    END IF;
            	ELSE
                	IF v_start = 'first' THEN
                    	SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), '-10-01');
                	ELSE
                    	SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), '-12-31');
                    END IF;
                END IF; 
			WHEN g_period_semester THEN
            	IF SUBSTR(p_timeval, 6, 2) <= 6 THEN
                	IF v_start = 'first' THEN
                    	SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), '-01-01');
                	ELSE
                    	SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), '-06-30');
                    END IF;
            	ELSE
                	IF v_start = 'first' THEN
                    	SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), '-07-01');
                    ELSE
                    	SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), '-12-31');
                    END IF;
                END IF; 
			WHEN g_period_year THEN
            	IF v_start = 'first' THEN
                	SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), '-01-01');
                ELSE
                	SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), '-12-31');
                END IF;
            ELSE 
            	SET v_retval = p_timeval;
		END CASE;
	ELSE 
		IF v_size < 5 THEN
        	SET v_retval = 'Invalid format';
    	ELSE
        	SET v_position = INSTR(p_timeval, g_time_separator);
            IF v_position <> 0 THEN
            #YYYY-%m/YYYY-%m format
            	SET v_retval = 'Invalid format';
        	ELSE
            	SET v_period = SUBSTR(p_timeval, 5, 1);

				IF v_period = '-' THEN
                	SET v_period = SUBSTR(p_timeval, 6, 1);

                        CASE v_period
                            WHEN g_period_day THEN
                                #YYYY-Dddd format
                                IF p_period_to = g_period_year THEN
                                    SET v_retval = SUBSTR(p_timeval, 1, 4);
                                ELSEIF p_period_to = g_period_semester THEN
                                    IF SUBSTR(p_timeval, 7, 3) <= DATE_FORMAT(STR_TO_DATE(CONCAT(SUBSTR(p_timeval, 1, 4), '0630'),'%Y%m%d'),'%j') THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '1');
                                    ELSE
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '2');
                                    END IF;
                                ELSEIF p_period_to = g_period_quarter THEN
                                    IF SUBSTR(p_timeval, 7, 3) <= DATE_FORMAT(STR_TO_DATE(CONCAT(SUBSTR(p_timeval, 1, 4), '0331'),'%Y%m%d'),'%j') THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '1');
                                    ELSEIF SUBSTR(p_timeval, 7, 3) <= DATE_FORMAT(STR_TO_DATE(CONCAT(SUBSTR(p_timeval, 1, 4), '0630'),'%Y%m%d'),'%j') THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '2');
                                    ELSEIF SUBSTR(p_timeval, 7, 3) <= DATE_FORMAT(STR_TO_DATE(CONCAT(SUBSTR(p_timeval, 1, 4), '0930'),'%Y%m%d'),'%j') THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '3');
                                    ELSE
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '4');
                                    END IF;
                                ELSEIF p_period_to = g_period_month THEN
                                    SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_month, DATE_FORMAT(date_add(STR_TO_DATE(CONCAT(SUBSTR(p_timeval, 1, 4), '0101'), '%Y%m%d'), INTERVAL SUBSTR(p_timeval, 7, 3) -1 DAY), '%m'));
                                ELSEIF p_period_to = g_period_week THEN
                                    SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_week, DATE_FORMAT(date_add(STR_TO_DATE(CONCAT(SUBSTR(p_timeval, 1, 4), '0101'), '%Y%m%d'), INTERVAL SUBSTR(p_timeval, 7, 3) -1 DAY), '%v'));
                                ELSE
                                    SET v_retval = p_timeval;
                                END IF;
                            WHEN g_period_week THEN
                                #YYYY-Www format
                                IF p_period_to = g_period_year THEN
                                    SET v_retval = SUBSTR(p_timeval, 1, 4);
                                ELSEIF p_period_to = g_period_semester THEN
                                    IF SUBSTR(p_timeval, 7, 2) <= DATE_FORMAT(STR_TO_DATE(CONCAT(SUBSTR(p_timeval, 1, 4), '0630'),'%Y%m%d'),'%v') THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '1');
                                    ELSE
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '2');
                                    END IF;
                                ELSEIF p_period_to = g_period_quarter THEN
                                    IF SUBSTR(p_timeval, 7, 2) <= DATE_FORMAT(STR_TO_DATE(CONCAT(SUBSTR(p_timeval, 1, 4), '0331'),'%Y%m%d'),'%v') THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '1');
                                    ELSEIF SUBSTR(p_timeval, 7, 2) <= DATE_FORMAT(STR_TO_DATE(CONCAT(SUBSTR(p_timeval, 1, 4), '0630'),'%Y%m%d'),'%v') THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '2');
                                    ELSEIF SUBSTR(p_timeval, 7, 2) <= DATE_FORMAT(STR_TO_DATE(CONCAT(SUBSTR(p_timeval, 1, 4), '0930'),'%Y%m%d'),'%v') THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '3');
                                    ELSE
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '4');
                                    END IF;
                                ELSEIF p_period_to = g_period_month THEN
                                    SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_month, DATE_FORMAT(vtl_iso_week_to_date(SUBSTR(p_timeval, 1, 4), SUBSTR(p_timeval, 7, 2)), '%m'));
                                ELSE
                                    SET v_retval = p_timeval;
                                END IF;
                            WHEN g_period_month THEN
                                #YYYY-Mmm format
                                IF p_period_to = g_period_year THEN
                                    SET v_retval = SUBSTR(p_timeval, 1, 4);
                                ELSEIF p_period_to = g_period_semester THEN
                                    IF SUBSTR(p_timeval, 7, 2) <= 6 THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '1');
                                    ELSE
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '2');
                                    END IF;
                                ELSEIF p_period_to = g_period_quarter THEN
                                    IF SUBSTR(p_timeval, 7, 2) <= 3 THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '1');
                                    ELSEIF SUBSTR(p_timeval, 7, 2) <= 6 THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '2');
                                    ELSEIF SUBSTR(p_timeval, 7, 2) <= 9 THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '3');
                                    ELSE
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '4');
                                    END IF;
                                ELSE
                                    SET v_retval = p_timeval;
                                END IF; 
                            WHEN g_period_quarter THEN
                                #YYYY-Qq format
                                IF p_period_to = g_period_year THEN
                                    SET v_retval = SUBSTR(p_timeval, 1, 4);
                                ELSEIF p_period_to = g_period_semester THEN
                                    IF SUBSTR(p_timeval, 7, 2) <= 2 THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '1');
                                    ELSE
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '2');
                                    END IF;
                                ELSE
                                    SET v_retval = p_timeval;
                                END IF; 
                            WHEN g_period_semester THEN
                                #YYYY-Ss format
                                IF p_period_to = g_period_year THEN
                                    #YYYY-xMM format
                                    SET v_retval = SUBSTR(p_timeval, 1, 4);
                                ELSE
                                    SET v_retval = p_timeval;
                                END IF; 
                            WHEN g_period_year THEN
                                #YYYY-A format
                                SET v_retval = SUBSTR(p_timeval, 1, 4);
                            ELSE 
                                IF v_size = 7 THEN
                                    #YYYY-%m format
                                    IF p_period_to = g_period_year THEN
                                        SET v_retval = SUBSTR(p_timeval, 1, 4);
                                    ELSEIF p_period_to = g_period_semester THEN
                                        IF SUBSTR(p_timeval, 6, 2) <= 6 THEN
                                            SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '1');
                                        ELSE
                                            SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '2');
                                        END IF;
                                    ELSEIF p_period_to = g_period_quarter THEN
                                        IF SUBSTR(p_timeval, 6, 2) <= 3 THEN
                                            SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '1');
                                        ELSEIF SUBSTR(p_timeval, 6, 2) <= 6 THEN
                                            SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '2');
                                        ELSEIF SUBSTR(p_timeval, 6, 2) <= 9 THEN
                                            SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '3');
                                        ELSE
                                            SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '4');
                                        END IF;
                                    ELSE
                                        SET v_retval = p_timeval;
                                    END IF; 
                                ELSE
                                    IF v_size = 8 THEN
                                        #YYYY-xMM format
                                        IF p_period_to = g_period_year THEN
                                            SET v_retval = SUBSTR(p_timeval, 1, 4);
                                        ELSEIF p_period_to = g_period_semester THEN
                                            IF SUBSTR(p_timeval, 7, 2) <= 6 THEN
                                                SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '1');
                                            ELSE
                                                SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '2');
                                            END IF;
                                        ELSEIF p_period_to = g_period_quarter THEN
                                            IF SUBSTR(p_timeval, 7, 2) <= 3 THEN
                                                SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '1');
                                            ELSEIF SUBSTR(p_timeval, 7, 2) <= 6 THEN
                                                SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '2');
                                            ELSEIF SUBSTR(p_timeval, 7, 2) <= 9 THEN
                                                SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '3');
                                            ELSE
                                                SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '4');
                                            END IF;
                                        ELSE
                                            SET v_retval = p_timeval;
                                        END IF; 
                                    ELSE
                                        SET v_retval = 'Invalid format';
                                    END IF; 
                                END IF;    
                        END CASE;
				ELSE
                        CASE v_period
                            WHEN g_period_day THEN
                                #YYYYDddd format
                                IF p_period_to = g_period_year THEN
                                    SET v_retval = SUBSTR(p_timeval, 1, 4);
                                ELSEIF p_period_to = g_period_semester THEN
                                    IF SUBSTR(p_timeval, 6, 3) <= DATE_FORMAT(STR_TO_DATE(CONCAT(SUBSTR(p_timeval, 1, 4), '0630'),'%Y%m%d'),'%j') THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '1');
                                    ELSE
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '2');
                                    END IF;
                                ELSEIF p_period_to = g_period_quarter THEN
                                    IF SUBSTR(p_timeval, 6, 3) <= DATE_FORMAT(STR_TO_DATE(CONCAT(SUBSTR(p_timeval, 1, 4), '0331'),'%Y%m%d'),'%j') THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '1');
                                    ELSEIF SUBSTR(p_timeval, 6, 3) <= DATE_FORMAT(STR_TO_DATE(CONCAT(SUBSTR(p_timeval, 1, 4), '0630'),'%Y%m%d'),'%j') THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '2');
                                    ELSEIF SUBSTR(p_timeval, 6, 3) <= DATE_FORMAT(STR_TO_DATE(CONCAT(SUBSTR(p_timeval, 1, 4), '0930'),'%Y%m%d'),'%j') THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '3');
                                    ELSE
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '4');
                                    END IF;
                                ELSEIF p_period_to = g_period_month THEN
                                    SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_month, DATE_FORMAT(date_add(STR_TO_DATE(CONCAT(SUBSTR(p_timeval, 1, 4), '0101'), '%Y%m%d'), INTERVAL SUBSTR(p_timeval, 6, 3) -1 DAY), '%m'));
                                ELSEIF p_period_to = g_period_week THEN
                                    SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_week, DATE_FORMAT(date_add(STR_TO_DATE(CONCAT(SUBSTR(p_timeval, 1, 4), '0101'), '%Y%m%d'), INTERVAL SUBSTR(p_timeval, 6, 3) -1 DAY), '%v'));
                                ELSE
                                    SET v_retval = p_timeval;
                                END IF;
                            WHEN g_period_week THEN
                                #YYYYWww format
                                IF p_period_to = g_period_year THEN
                                    SET v_retval = SUBSTR(p_timeval, 1, 4);
                                ELSEIF p_period_to = g_period_semester THEN
                                    IF SUBSTR(p_timeval, 6, 2) <= DATE_FORMAT(STR_TO_DATE(CONCAT(SUBSTR(p_timeval, 1, 4), '0630'),'%Y%m%d'),'%v') THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '1');
                                    ELSE
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '2');
                                    END IF;
                                ELSEIF p_period_to = g_period_quarter THEN
                                    IF SUBSTR(p_timeval, 6, 2) <= DATE_FORMAT(STR_TO_DATE(CONCAT(SUBSTR(p_timeval, 1, 4), '0331'),'%Y%m%d'),'%v') THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '1');
                                    ELSEIF SUBSTR(p_timeval, 6, 2) <= DATE_FORMAT(STR_TO_DATE(CONCAT(SUBSTR(p_timeval, 1, 4), '0630'),'%Y%m%d'),'%v') THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '2');
                                    ELSEIF SUBSTR(p_timeval, 6, 2) <= DATE_FORMAT(STR_TO_DATE(CONCAT(SUBSTR(p_timeval, 1, 4), '0930'),'%Y%m%d'),'%v') THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '3');
                                    ELSE
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '4');
                                    END IF;
                                ELSEIF p_period_to = g_period_month THEN
                                    SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_month, DATE_FORMAT(vtl_iso_week_to_date(SUBSTR(p_timeval, 1, 4), SUBSTR(p_timeval, 6, 2)), '%m'));
                                ELSE
                                    SET v_retval = p_timeval;
                                END IF;
                            WHEN g_period_month THEN
                                #YYYYMmm format
                                IF p_period_to = g_period_year THEN
                                    SET v_retval = SUBSTR(p_timeval, 1, 4);
                                ELSEIF p_period_to = g_period_semester THEN
                                    IF SUBSTR(p_timeval, 6, 2) <= 6 THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '1');
                                    ELSE
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '2');
                                    END IF;
                                ELSEIF p_period_to = g_period_quarter THEN
                                    IF SUBSTR(p_timeval, 6, 2) <= 3 THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '1');
                                    ELSEIF SUBSTR(p_timeval, 6, 2) <= 6 THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '2');
                                    ELSEIF SUBSTR(p_timeval, 6, 2) <= 9 THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '3');
                                    ELSE
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '4');
                                    END IF;
                                ELSE
                                    SET v_retval = p_timeval;
                                END IF; 
                            WHEN g_period_quarter THEN
                                #YYYYQq format
                                IF p_period_to = g_period_year THEN
                                    SET v_retval = SUBSTR(p_timeval, 1, 4);
                                ELSEIF p_period_to = g_period_semester THEN
                                    IF SUBSTR(p_timeval, 6, 2) <= 2 THEN
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '1');
                                    ELSE
                                        SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '2');
                                    END IF;
                                ELSE
                                    SET v_retval = p_timeval;
                                END IF; 
                            WHEN g_period_semester THEN
                                #YYYYSs format
                                IF p_period_to = g_period_year THEN
                                    #YYYY-xMM format
                                    SET v_retval = SUBSTR(p_timeval, 1, 4);
                                ELSE
                                    SET v_retval = p_timeval;
                                END IF; 
                            WHEN g_period_year THEN
                                #YYYYA format
                                SET v_retval = SUBSTR(p_timeval, 1, 4);
                            ELSE 
                                IF v_size = 6 THEN
                                    #YYYYMM format
                                    IF p_period_to = g_period_year THEN
                                        SET v_retval = SUBSTR(p_timeval, 1, 4);
                                    ELSEIF p_period_to = g_period_semester THEN
                                        IF SUBSTR(p_timeval, 5, 2) <= 6 THEN
                                            SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '1');
                                        ELSE
                                            SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '2');
                                        END IF;
                                    ELSEIF p_period_to = g_period_quarter THEN
                                        IF SUBSTR(p_timeval, 5, 2) <= 3 THEN
                                            SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '1');
                                        ELSEIF SUBSTR(p_timeval, 5, 2) <= 6 THEN
                                            SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '2');
                                        ELSEIF SUBSTR(p_timeval, 5, 2) <= 9 THEN
                                            SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '3');
                                        ELSE
                                            SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '4');
                                        END IF;
                                    ELSE
                                        SET v_retval = p_timeval;
                                    END IF; 
                                ELSE
                                    IF v_size = 7 THEN
                                        #YYYYxMM format
                                        IF p_period_to = g_period_year THEN
                                            SET v_retval = SUBSTR(p_timeval, 1, 4);
                                        ELSEIF p_period_to = g_period_semester THEN
                                            IF SUBSTR(p_timeval, 6, 2) <= 6 THEN
                                                SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '1');
                                            ELSE
                                                SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_semester, '2');
                                            END IF;
                                        ELSEIF p_period_to = g_period_quarter THEN
                                            IF SUBSTR(p_timeval, 6, 2) <= 3 THEN
                                                SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '1');
                                            ELSEIF SUBSTR(p_timeval, 6, 2) <= 6 THEN
                                                SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '2');
                                            ELSEIF SUBSTR(p_timeval, 6, 2) <= 9 THEN
                                                SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '3');
                                            ELSE
                                                SET v_retval = CONCAT(SUBSTR(p_timeval, 1, 4), g_period_quarter, '4');
                                            END IF;
                                        ELSE
                                            SET v_retval = p_timeval;
                                        END IF; 
                                    ELSE
                                        SET v_retval = 'Invalid format';
                                    END IF; 
                                END IF;    
                        END CASE;
                    END IF;   
                END IF;    
	END IF;
END CASE;

RETURN v_retval;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_standardize_number`(p_value VARCHAR(50)) RETURNS double
    DETERMINISTIC
BEGIN
DECLARE v_standard FLOAT8; 
DECLARE v_perc BOOLEAN;
DECLARE v_numeratore FLOAT8;
DECLARE v_denominatore FLOAT8;
DECLARE v_power INT;
DECLARE v_int INT;
	
CASE INSTR(SUBSTR(p_value, 1),'%') 
WHEN 0 THEN 
	SET v_perc = false;
ELSE 
	SET v_perc = true;
END CASE; -- mi dice se è una percentuale
SET v_int = length(REPLACE(REPLACE(p_value, '.','/'),',','/')) - length(substring_index(REPLACE(REPLACE(p_value, '.','/'),',','/'), '/',-1));
SET v_numeratore = (REPLACE(REPLACE (REPLACE (p_value, '%', ''), '.', ''),',',''));  #tolgo '%' e punti e virgole 
CASE v_perc 
	WHEN TRUE then
		if v_int <> 0 then
    		SET v_power = length(substring_index(REPLACE(REPLACE(p_value, '.','/'),',','/'), '/',-1)) - 1;
    	else
    		SET v_power = 0;
    	end if;
    ELSE 
    	if v_int <> 0 then
    		SET v_power = length(substring_index(REPLACE(REPLACE(p_value, '.','/'),',','/'), '/',-1));
    	else
    		SET v_power = 0;
    	end if;
END CASE; 
SET v_denominatore = POWER(10,v_power); 
CASE v_perc 
	WHEN TRUE THEN 
		SET v_standard = v_numeratore/(100*v_denominatore);
    ELSE 
    	SET v_standard = v_numeratore / (v_denominatore);
END CASE;
          
RETURN v_standard ;
/*EXCEPTION
        WHEN NO_DATA_FOUND
           THEN
              NULL;
        WHEN OTHERS
           THEN
              -- Consider logging the error and then re-raise
              RETURN NULL ;--RAISE;*/
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_cast_to_int`(p_value VARCHAR(50),p_datatype VARCHAR(50)) RETURNS int(11)
    DETERMINISTIC
BEGIN
	DECLARE v_output_value INTEGER;
	DECLARE g_true_value VARCHAR(4);
	DECLARE g_false_value VARCHAR(5);
	SET g_true_value = 'TRUE';
	SET g_false_value = 'FALSE';
	case p_datatype 
            when 'STRING' then SET v_output_value =  REPLACE(p_value, ',', '.');
            when 'NUMBER' then SET v_output_value =  vtl_standardize_number(p_value); 
            when 'BOOLEAN' then
                case 
                    when UPPER(p_value) IN ( g_true_value, '1') 
                        then SET v_output_value = 1; 
                        else SET v_output_value = 0; 
                end case;
        end case; 
  
    return v_output_value;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_cast_to_number`(p_value VARCHAR(50),p_datatype VARCHAR(50), p_mask VARCHAR(50)) RETURNS float
    DETERMINISTIC
BEGIN
	DECLARE v_output_value FLOAT4;
	DECLARE g_true_value VARCHAR(4);
	DECLARE g_false_value VARCHAR(5);
	SET g_true_value = 'TRUE';
	SET g_false_value = 'FALSE';
	case p_datatype 
            when 'BOOLEAN' then 
                case 
                    when UPPER(p_value) IN (g_true_value, '1') 
                        then SET v_output_value = 1; 
                        else SET v_output_value = 0; 
                end case;
            when 'STRING' then
            case
            	when CAST(REPLACE(p_value,',','.') AS DECIMAL(10,2)) > CAST(REPLACE(REPLACE(p_mask,'D','9'),',','.') AS DECIMAL(10,2)) then
           			SET v_output_value := p_mask;
           		else
            		SET v_output_value := CAST(REPLACE(p_value, ',', '.') AS DECIMAL(10,2));
            end case;
            when 'INTEGER' then SET v_output_value = vtl_standardize_number(p_value);
        end case;
  
    return v_output_value;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_cast_to_boolean`(p_value VARCHAR(50),p_datatype VARCHAR(50)) RETURNS tinyint(1)
    DETERMINISTIC
BEGIN
	DECLARE g_true_value VARCHAR(4);
	DECLARE g_false_value VARCHAR(5);
	DECLARE v_output_value BOOLEAN;
	SET g_true_value = 'TRUE';
	SET g_false_value = 'FALSE';
	
	case p_datatype 
            when 'INTEGER' then 
                case 
                    when p_value <> '0' then SET v_output_value = TRUE;
                    else SET v_output_value = FALSE;
                end case;
            when 'NUMBER' then 
                case 
                    when p_value <> '0' then SET v_output_value = TRUE;
                    else SET v_output_value = FALSE;
                end case;
            /*when 'STRING' then 
                case 
                    when p_value = g_true_value then SET v_output_value = TRUE;
                    when p_value = g_false_value then SET v_output_value = FALSE;
                end case;*/                
        end case; 
  
        return v_output_value;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_cast_to_time`(p_value VARCHAR(50),p_datatype VARCHAR(50),p_mask VARCHAR(50)) RETURNS varchar(50) CHARSET utf8
    DETERMINISTIC
BEGIN
DECLARE v_period VARCHAR(1);
DECLARE v_year VARCHAR(4);
DECLARE v_first_char INTEGER;
DECLARE v_output_value varchar(50); 
DECLARE g_true_value VARCHAR(4);
DECLARE g_false_value VARCHAR(5);
DECLARE g_time_separator VARCHAR(1);
DECLARE g_period_day VARCHAR(1);
DECLARE g_period_week VARCHAR(1);
DECLARE g_period_month VARCHAR(1);
DECLARE g_period_quarter VARCHAR(1);
DECLARE g_period_semester VARCHAR(1);
DECLARE g_period_year VARCHAR(1);
DECLARE v_position INTEGER;
DECLARE v_left_mask VARCHAR(50);
DECLARE v_right_mask VARCHAR(50);
DECLARE v_left_value VARCHAR(50);
DECLARE v_righ_value VARCHAR(50);

SET g_true_value = 'TRUE';
SET g_false_value = 'FALSE';
SET g_time_separator = '/';
SET g_period_day = 'D';
SET g_period_week = 'W';
SET g_period_month = 'M';
SET g_period_quarter = 'Q';
SET g_period_semester = 'S';
SET g_period_year = 'A';
	
	case p_datatype 
            when 'STRING' then
            	IF (LENGTH(p_value) <> LENGTH(p_mask))
				THEN
					SIGNAL SQLSTATE '45000'
					SET MESSAGE_TEXT = 'Invalid cast.';
				END IF;
            	
                SET v_position = instr(p_mask, g_time_separator);
                IF v_position <> 0 THEN
                    SET v_left_mask = TRIM(SUBSTR(p_mask, 1, v_position - 1));
                    case
            			when v_left_mask = 'YYYY-MM-DD' COLLATE utf8mb4_0900_as_cs then
            			SET v_left_mask = '%Y-%m-%d';
            		end case;
                    SET v_right_mask = TRIM(SUBSTR(p_mask, v_position + 1));
                    case
            			when v_right_mask = 'YYYY-MM-DD' COLLATE utf8mb4_0900_as_cs then
            			SET v_right_mask = '%Y-%m-%d';
            		end case;

                    SET v_position = instr(p_value, g_time_separator);

                    SET v_left_value = TRIM(SUBSTR(p_value, 1, v_position - 1));
                    SET v_righ_value = TRIM(SUBSTR(p_value, v_position + 1));

                    SET v_output_value = CONCAT(DATE_FORMAT(STR_TO_DATE(v_left_value, v_left_mask), v_left_mask) , g_time_separator , DATE_FORMAT(STR_TO_DATE(v_righ_value, v_right_mask), v_right_mask));
                ELSE
                	SIGNAL SQLSTATE '45000'
					SET MESSAGE_TEXT = 'Invalid cast.';
                END IF;
             
           	when 'DATE' then SET v_output_value = CONCAT(DATE_FORMAT(p_value, '%Y-%m-%d') , '/' , DATE_FORMAT(p_value, '%Y-%m-%d'));
            when 'TIME_PERIOD' then               
                SET v_year = SUBSTR(p_value, 1, 4);
                
                SET v_first_char = 5;
                SET v_period = SUBSTR(p_value, v_first_char, 1);

                IF v_period = '-' THEN
                    SET v_first_char = 6;
                    SET v_period = SUBSTR(p_value, v_first_char, 1);
                END IF;
                
                SET v_first_char = v_first_char +1;
                
                CASE v_period
                    WHEN g_period_day THEN
                        SET v_output_value = CONCAT(DATE_FORMAT(STR_TO_DATE(CONCAT(v_year , SUBSTR(p_value, v_first_char, 3)), '%Y%j'), '%Y-%m-%d') , '/' , DATE_FORMAT(STR_TO_DATE(CONCAT(v_year , SUBSTR(p_value, v_first_char, 3)), '%Y%j'), '%Y-%m-%d'));
                    WHEN g_period_week THEN
                        #SET v_output_value = CONCAT(DATE_FORMAT(trunc(STR_TO_DATE(CONCAT(v_year , DATE_FORMAT(SUBSTR(p_value, v_first_char, 2) * 7, '000')), '%Y%j'), 'IW'), '%Y-%m-%d') , '/' , DATE_FORMAT(trunc(STR_TO_DATE(CONCAT(v_year , DATE_FORMAT(SUBSTR(p_value, v_first_char, 2) * 7, '000')), '%Y%j'), 'IW') +6, '%Y-%m-%d'));
                    	/*SET v_output_value = CONCAT(DATE_FORMAT( STR_TO_DATE(CONCAT(YEARWEEK(CONCAT(v_year , DATE_FORMAT(SUBSTR(p_value, v_first_char, 2) * 7, '000')), 5), ' Monday'), '%Y%j') , '%Y-%m-%d')
 											, '/' , DATE_FORMAT( STR_TO_DATE(CONCAT(YEARWEEK(CONCAT(v_year , DATE_FORMAT(SUBSTR(p_value, v_first_char, 2) * 7, '000')), 5), ' Monday'), '%Y%j') +6, '%Y-%m-%d'));*/
                    	SET v_output_value = CONCAT(vtl_iso_week_to_date(v_year, SUBSTR(p_value, v_first_char, 2)), '/' ,date_add(vtl_iso_week_to_date(v_year, SUBSTR(p_value, v_first_char, 2)), INTERVAL 6 DAY));
                    WHEN g_period_month THEN
                        SET v_output_value = CONCAT(STR_TO_DATE(CONCAT(v_year , SUBSTR(p_value, v_first_char, 2) , '01'), '%Y%m%d') , '/' , last_day(STR_TO_DATE(CONCAT(v_year , SUBSTR(p_value, v_first_char, 2) , '01'), '%Y%m%d')));
                    WHEN g_period_quarter THEN
                        IF SUBSTR(p_value, v_first_char, 1) = '1' THEN
                            SET v_output_value = CONCAT(STR_TO_DATE(CONCAT(v_year , '0101'), '%Y%m%d') , '/' , STR_TO_DATE(CONCAT(v_year , '0331'), '%Y%m%d'));
                        ELSEIF SUBSTR(p_value, v_first_char, 1) = '2' THEN
                            SET v_output_value = CONCAT(STR_TO_DATE(CONCAT(v_year , '0401'), '%Y%m%d') , '/' , STR_TO_DATE(CONCAT(v_year , '0630'), '%Y%m%d'));
                        ELSEIF SUBSTR(p_value, v_first_char, 1) = '3' THEN
                            SET v_output_value = CONCAT(STR_TO_DATE(CONCAT(v_year , '0701'), '%Y%m%d') , '/' , STR_TO_DATE(CONCAT(v_year , '0930'), '%Y%m%d'));
                        ELSE
                            SET v_output_value = CONCAT(STR_TO_DATE(CONCAT(v_year , '1001'), '%Y%m%d') , '/' , STR_TO_DATE(CONCAT(v_year , '1231'), '%Y%m%d'));
                        END IF;             
                    WHEN g_period_semester THEN
                        IF SUBSTR(p_value, v_first_char, 1) = '1' THEN
                            SET v_output_value = CONCAT(STR_TO_DATE(CONCAT(v_year , '0101'), '%Y%m%d') , '/' , STR_TO_DATE(CONCAT(v_year , '0630'), '%Y%m%d'));
                        ELSE
                            SET v_output_value = CONCAT(STR_TO_DATE(CONCAT(v_year , '0701'), '%Y%m%d') , '/' , STR_TO_DATE(CONCAT(v_year , '1231'), '%Y%m%d'));
                        END IF;
                    WHEN g_period_year THEN
                            SET v_output_value = CONCAT(STR_TO_DATE(CONCAT(v_year , '0101'), '%Y%m%d') , '/' , STR_TO_DATE(CONCAT(v_year , '1231'), '%Y%m%d'));
                END CASE;                
        end case; 
  
        return v_output_value;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_cast_to_time_date`(p_value DATE,p_datatype VARCHAR(50),p_mask VARCHAR(50)) RETURNS varchar(50) CHARSET utf8
    DETERMINISTIC
BEGIN
	DECLARE v_output_value VARCHAR(50);
	
	case p_datatype 
            when 'DATE' then
            	SET v_output_value = CONCAT(DATE_FORMAT(p_value, '%Y-%m-%d') , '/' , DATE_FORMAT(p_value, '%Y-%m-%d'));
    end case; 
    return v_output_value;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_cast_to_date`(p_value VARCHAR(50),p_datatype VARCHAR(50),p_mask VARCHAR(50)) RETURNS date
    DETERMINISTIC
BEGIN
DECLARE v_start VARCHAR(10);
DECLARE v_period VARCHAR(1);
DECLARE v_year VARCHAR(4);
DECLARE v_first_char INTEGER;
DECLARE v_output_value DATE; 

DECLARE g_true_value VARCHAR(4);
DECLARE g_false_value VARCHAR(5);
DECLARE g_time_separator VARCHAR(1);
DECLARE g_period_day VARCHAR(1);
DECLARE g_period_week VARCHAR(1);
DECLARE g_period_month VARCHAR(1);
DECLARE g_period_quarter VARCHAR(1);
DECLARE g_period_semester VARCHAR(1);
DECLARE g_period_year VARCHAR(1);

SET g_true_value = 'TRUE';
SET g_false_value = 'FALSE';
SET g_time_separator = '/';
SET g_period_day = 'D';
SET g_period_week = 'W';
SET g_period_month = 'M';
SET g_period_quarter = 'Q';
SET g_period_semester = 'S';
SET g_period_year = 'A';
	
	case p_datatype 
            when 'STRING' then
            	IF (LENGTH(p_value) <> LENGTH(p_mask))
				THEN
					SIGNAL SQLSTATE '45000'
					SET MESSAGE_TEXT = 'Invalid cast.';
				END IF;
            	case
            		when p_mask = 'YYYY-MM-DD' COLLATE utf8mb4_0900_as_cs then
            			SET v_output_value = STR_TO_DATE( p_value, '%Y-%m-%d'); 
            	end case;
            when 'TIME_PERIOD' then 
                SET v_start = coalesce(p_mask, 'START');
                
                SET v_year = SUBSTR(p_value, 1, 4);
                
                SET v_first_char = 5;
                SET v_period = SUBSTR(p_value, v_first_char, 1);

                IF v_period = '-' THEN
                    SET v_first_char = 6;
                    SET v_period = SUBSTR(p_value, v_first_char, 1);
                END IF;
                
                SET v_first_char = v_first_char +1;
                
                CASE v_period
                    WHEN g_period_day THEN
                        SET v_output_value = date_add(STR_TO_DATE(CONCAT(v_year , '0101' ), '%Y%m%d'), INTERVAL SUBSTR(p_value, v_first_char, 3)-1 DAY);
                    WHEN g_period_week THEN
                        IF v_start = 'START' THEN
                        	SET v_output_value = vtl_iso_week_to_date(v_year, SUBSTR(p_value, v_first_char, 2));#STR_TO_DATE(CONCAT(v_year , DATE_FORMAT(SUBSTR(p_value, v_first_char, 2) * 7, '000')), '%Y%m%d');
                        ELSE
                        	SET v_output_value = date_add(vtl_iso_week_to_date(v_year, SUBSTR(p_value, v_first_char, 2)), INTERVAL 6 DAY);
                        END IF;
                    WHEN g_period_month THEN
                        IF v_start = 'START' THEN
                            SET v_output_value = STR_TO_DATE(CONCAT(v_year , SUBSTR(p_value, v_first_char, 2) , '01'), '%Y%m%d');
                        ELSE
                            SET v_output_value = last_day(STR_TO_DATE(CONCAT(v_year , SUBSTR(p_value, v_first_char, 2) , '01'), '%Y%m%d'));
                        END IF;
                    WHEN g_period_quarter THEN
                        IF SUBSTR(p_value, v_first_char, 1) = '1' THEN
                            IF v_start = 'START' THEN
                                SET v_output_value = STR_TO_DATE(CONCAT(v_year , '0101'), '%Y%m%d');
                            ELSE
                                SET v_output_value = STR_TO_DATE(CONCAT(v_year , '0331'), '%Y%m%d');
                            END IF;
                        ELSEIF SUBSTR(p_value, v_first_char, 1) = '2' THEN
                            IF v_start = 'START' THEN
                                SET v_output_value = STR_TO_DATE(CONCAT(v_year , '0401'), '%Y%m%d');
                            ELSE
                                SET v_output_value = STR_TO_DATE(CONCAT(v_year , '0630'), '%Y%m%d');
                            END IF;
                        ELSEIF SUBSTR(p_value, v_first_char, 1) = '3' THEN
                            IF v_start = 'START' THEN
                                SET v_output_value = STR_TO_DATE(CONCAT(v_year , '0701'), '%Y%m%d');
                            ELSE
                                SET v_output_value = STR_TO_DATE(CONCAT(v_year , '0930'), '%Y%m%d');
                            END IF;
                        ELSE
                            IF v_start = 'START' THEN
                                SET v_output_value = STR_TO_DATE(CONCAT(v_year , '1001'), '%Y%m%d');
                            ELSE
                                SET v_output_value = STR_TO_DATE(CONCAT(v_year , '1231'), '%Y%m%d');
                            END IF;
                        END IF;             
                    WHEN g_period_semester THEN
                        IF SUBSTR(p_value, v_first_char, 1) = '1' THEN
                            IF v_start = 'START' THEN
                                SET v_output_value = STR_TO_DATE(CONCAT(v_year , '0101'), '%Y%m%d');
                            ELSE
                                SET v_output_value = STR_TO_DATE(CONCAT(v_year , '0630'), '%Y%m%d');
                            END IF;
                        ELSE
                            IF v_start = 'START' THEN
                                SET v_output_value = STR_TO_DATE(CONCAT(v_year , '0701'), '%Y%m%d');
                            ELSE
                                SET v_output_value = STR_TO_DATE(CONCAT(v_year , '1231'), '%Y%m%d');
                            END IF;
                        END IF;
                    WHEN g_period_year THEN
                        IF v_start = 'START' THEN
                            SET v_output_value = STR_TO_DATE(CONCAT(v_year , '0101'), '%Y%m%d');
                        ELSE
                            SET v_output_value = STR_TO_DATE(CONCAT(v_year , '1231'), '%Y%m%d');
                        END IF;
                END CASE;                
        end case; 
  
        return v_output_value;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_cast_to_timeperiodv2`(p_value DATE,p_datatype VARCHAR(50),p_mask VARCHAR(50)) RETURNS varchar(50) CHARSET utf8
    DETERMINISTIC
BEGIN
	return vtl_cast_to_timeperiod (DATE_FORMAT(p_value, p_mask), p_datatype, p_mask);
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_cast_to_timeperiod`(p_value VARCHAR(50),p_datatype VARCHAR(50),p_mask VARCHAR(50)) RETURNS varchar(50) CHARSET utf8
    DETERMINISTIC
BEGIN
	DECLARE v_retval VARCHAR(50);
	DECLARE v_output_value VARCHAR(50);
	DECLARE g_period_day VARCHAR(1);
	SET g_period_day = 'D';

	case p_datatype 
            when 'DATE' then SET v_output_value = CONCAT(DATE_FORMAT(STR_TO_DATE(p_value, p_mask), '%Y') , g_period_day , DATE_FORMAT(STR_TO_DATE(p_value, p_mask), '%j'));
            when 'STRING' then 
           		SET v_retval = vtl_period_indicator(p_value);
           		IF v_retval = 'Invalid format' THEN
            		SET v_output_value = 'Not castable value';
            	ELSE
            		SET v_output_value = p_value;
            	END IF;
    end case;
  
    return v_output_value;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_cast_to_string`(p_value VARCHAR(50),p_datatype VARCHAR(50),p_mask VARCHAR(50)) RETURNS varchar(50) CHARSET utf8
    DETERMINISTIC
BEGIN
	DECLARE v_output_value VARCHAR(50);
	DECLARE g_true_value VARCHAR(4);
	DECLARE g_false_value VARCHAR(5);
	SET g_true_value = 'TRUE';
	SET g_false_value = 'FALSE';
	case p_datatype 
            when 'INTEGER' then SET v_output_value =  p_value;
            when 'NUMBER' then SET v_output_value =  p_value; 
            when 'BOOLEAN' then 
                            case when UPPER(p_value) IN (g_true_value, '1') 
                                then SET v_output_value = '1'; 
                                else SET v_output_value = '0'; 
                            end case;
            when 'TIME' then
                case
                    when p_mask = 'YYYY-MM-DD/YYYY-MM-DD' COLLATE utf8mb4_0900_as_cs then
                        SET v_left_mask = '%Y-%m-%d';
                        SET v_right_mask = '%Y-%m-%d';
                        SET v_position = instr(p_value, g_time_separator);
                        SET v_left_value = TRIM(SUBSTR(p_value, 1, v_position - 1));
                        SET v_righ_value = TRIM(SUBSTR(p_value, v_position + 1));
                        SET v_output_value = CONCAT(DATE_FORMAT(STR_TO_DATE(v_left_value, v_left_mask), v_left_mask) , g_time_separator , DATE_FORMAT(STR_TO_DATE(v_righ_value, v_right_mask), v_right_mask));
                end case;
            when 'DATE' then
                case
                    when p_mask = 'YYYY-MM-DD' COLLATE utf8mb4_0900_as_cs then
                        SET v_output_value = STR_TO_DATE(p_value, '%Y-%m-%d');
                end case;
            when 'DURATION' then SET v_output_value = p_value; #to_char(p_value);
            when 'TIME_PERIOD' then SET v_output_value = p_value;
    end case; 
  
    return v_output_value;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_cast_to_string_date`(p_value DATE,p_datatype VARCHAR(50),p_mask VARCHAR(50)) RETURNS varchar(50) CHARSET utf8
    DETERMINISTIC
BEGIN
	DECLARE v_output_value VARCHAR(50);
	
	case p_datatype 
            
            when 'DATE' then
            	case
            		when p_mask = 'YYYY-MM-DD' COLLATE utf8mb4_0900_as_cs then
            			SET v_output_value = DATE_FORMAT(p_value, '%Y-%m-%d');
            	
            		when p_mask = 'YYYY/MM/DD' COLLATE utf8mb4_0900_as_cs then
            			SET v_output_value = DATE_FORMAT(p_value, '%Y/%m/%d');
            	end case;
            
    end case; 
  
    return v_output_value;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`vtl_cast_to_duration`(p_value VARCHAR(50),p_datatype VARCHAR(50),p_mask VARCHAR(50)) RETURNS varchar(100) CHARSET utf8
    DETERMINISTIC
BEGIN
	
	DECLARE V_LN_MASK NUMERIC;
	DECLARE V_LN_VALUE NUMERIC;
	DECLARE V_MASK VARCHAR(50);
	DECLARE V_VALUE VARCHAR(50);
	DECLARE V_VALUE_TMP VARCHAR(50);
	DECLARE V_VALUE_N DECIMAL(15,2);
	DECLARE V_P_MASK VARCHAR(1000);
	DECLARE V_P_VALUE VARCHAR(50);
	DECLARE V_CAST VARCHAR(2);
	DECLARE V_OUTPUT_VALUE VARCHAR(50);
	DECLARE V_LOOP NUMERIC;
	DECLARE V_SEARCH_CHAR VARCHAR(1);
	DECLARE V_COUNT_PERIOD NUMERIC;

	DECLARE PERIOD_SEPARATOR VARCHAR(2);
	SET PERIOD_SEPARATOR = '\\';
	SET V_P_MASK = '';
	SET V_P_VALUE = ''; 

	SET V_CAST = 'OK';
	SET V_LOOP = 1;
	SET V_COUNT_PERIOD = 0;

	SET V_OUTPUT_VALUE = p_value;
	SET V_VALUE = p_value;
	SET V_VALUE_TMP = p_value;
	SET V_MASK = p_mask;
	
	SELECT LENGTH(REPLACE(p_mask, PERIOD_SEPARATOR, '')) INTO V_LN_MASK FROM DUAL;
	
	IF(V_LN_MASK <> LENGTH(p_value) OR (V_VALUE NOT LIKE 'P%') OR (V_MASK NOT LIKE '\\\\P%'))
	THEN
		SET V_CAST := 'KO';
	ELSE
		SELECT LENGTH(V_MASK) INTO V_LN_MASK FROM DUAL;
		WHILE (V_LOOP < V_LN_MASK) DO
			SET V_P_MASK := CONCAT(V_P_MASK, SUBSTR(V_MASK, LOCATE(PERIOD_SEPARATOR, V_MASK, V_LOOP) +1, 1));
						
			IF (V_P_MASK IS NOT NULL)
			THEN
				SET V_LOOP := LOCATE(PERIOD_SEPARATOR, V_MASK, V_LOOP) +2;
			ELSE
				SET V_LOOP := V_LN_MASK +1; #EXIT LOOP
			END IF;
		END WHILE;
	
		SELECT LENGTH(V_VALUE) INTO V_LN_VALUE FROM DUAL;
		SELECT LENGTH(V_P_MASK) INTO V_COUNT_PERIOD FROM DUAL;
		SET V_LOOP := 1;
				
		WHILE (V_LOOP <= V_COUNT_PERIOD) DO
			SET V_SEARCH_CHAR := SUBSTR(V_P_MASK, V_LOOP, 1);
			SET V_P_VALUE  := CONCAT(V_P_VALUE, SUBSTR(V_VALUE_TMP, INSTR(V_VALUE_TMP, V_SEARCH_CHAR) , 1));
					
			IF ((V_P_VALUE IS NOT NULL) AND INSTR(V_VALUE_TMP, V_SEARCH_CHAR) > 0 )
			THEN
				SET V_VALUE_TMP := SUBSTR(V_VALUE_TMP, INSTR(V_VALUE_TMP, V_SEARCH_CHAR));
				SET V_VALUE := REPLACE(V_VALUE, V_SEARCH_CHAR, '');
			END IF;
		
			SET V_LOOP := V_LOOP + 1;
		END WHILE;
		SET V_VALUE := REPLACE(V_VALUE, 'T', '');

		IF (V_P_MASK = V_P_VALUE)
		THEN
			SET V_VALUE_N := CAST(V_VALUE AS DECIMAL(15,2));
		ELSE
			SET V_CAST := 'KO';
		END IF;
	
	END IF;
	
	IF (V_CAST = 'KO')
	THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Invalid cast.';
	END IF;

	RETURN V_OUTPUT_VALUE;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`convert_from_date`(p_timeval DATE,p_period VARCHAR(50),date_value VARCHAR(50)) RETURNS varchar(50) CHARSET utf8
    DETERMINISTIC
BEGIN
	
DECLARE v_position INTEGER;
DECLARE v_retval VARCHAR(50);
DECLARE g_true_value VARCHAR(4);
DECLARE g_false_value VARCHAR(5);
DECLARE g_time_separator VARCHAR(1);
DECLARE g_period_day VARCHAR(1);
DECLARE g_period_week VARCHAR(1);
DECLARE g_period_month VARCHAR(1);
DECLARE g_period_quarter VARCHAR(1);
DECLARE g_period_semester VARCHAR(1);
DECLARE g_period_year VARCHAR(1);
DECLARE g_period_date_time VARCHAR(1);

SET g_true_value = 'TRUE';
SET g_false_value = 'FALSE';
SET g_time_separator = '/';
SET g_period_day = 'D';
SET g_period_week = 'W';
SET g_period_month = 'M';
SET g_period_quarter = 'Q';
SET g_period_semester = 'S';
SET g_period_year = 'A';
SET g_period_date_time = 'T';
	
 CASE p_period
            WHEN g_period_day THEN
                #%Y%jd format
                SET v_retval = CONCAT(date_format(p_timeval, '%Y') , g_period_day , date_format(p_timeval, '%j'));
            WHEN g_period_week THEN
                #%YWww format
                SET v_retval = CONCAT(date_format(p_timeval, '%Y') , g_period_week , date_format(p_timeval, '%u'));
            WHEN g_period_month THEN
                SET v_position = INSTR(date_value, g_time_separator);
                
                IF v_position <> 0 THEN
                    #%Y-%m/%Y-%m format
                    SET v_retval = CONCAT(date_format(p_timeval, '%Y') , '-' , SUBSTR(date_value, 6, 2) , g_time_separator , date_format(p_timeval, '%Y') , '-' , SUBSTR(date_value, 14, 2));
                ELSE
                    #%Y%mm format
                    SET v_retval = CONCAT(date_format(p_timeval, '%Y') , g_period_month , date_format(p_timeval, '%m'));
                END IF; 
            WHEN g_period_quarter THEN
                #%YQq format
                IF date_format(p_timeval, '%m') <= 3 THEN
                    SET v_retval = CONCAT(date_format(p_timeval, '%Y') , g_period_quarter , '1');
                ELSEIF date_format(p_timeval, '%m') <= 6 THEN
                    SET v_retval = CONCAT(date_format(p_timeval, '%Y') , g_period_quarter , '2');
                ELSEIF date_format(p_timeval, '%m') <= 9 THEN
                    SET v_retval = CONCAT(date_format(p_timeval, '%Y') , g_period_quarter , '3');
                ELSE
                    SET v_retval = CONCAT(date_format(p_timeval, '%Y') , g_period_quarter , '4');
                END IF; 
            WHEN g_period_semester THEN
                #%YSs format
                IF date_format(p_timeval, '%m') <= 6 THEN
                    SET v_retval = CONCAT(date_format(p_timeval, '%Y') , g_period_semester , '1');
                ELSE
                    SET v_retval = CONCAT(date_format(p_timeval, '%Y') , g_period_semester , '2');
                END IF; 
            WHEN g_period_year THEN
                #%YA format
                SET v_retval = date_format(p_timeval, '%Y');
            WHEN g_period_date_time THEN
                #%Y-%m-DD
                SET v_retval = CONCAT(date_format(p_timeval, '%Y') , '-' , SUBSTR(date_value, 6, 5));
        END CASE;        
    
        RETURN v_retval;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` FUNCTION `SV_VTL_SQL`.`convert_to_date`(p_timeval VARCHAR(50)) RETURNS date
    DETERMINISTIC
BEGIN
	
DECLARE v_size INTEGER;
DECLARE v_position INTEGER;
DECLARE v_period VARCHAR(1);
DECLARE v_retval DATE;

DECLARE g_true_value VARCHAR(4);
DECLARE g_false_value VARCHAR(5);
DECLARE g_time_separator VARCHAR(1);
DECLARE g_period_day VARCHAR(1);
DECLARE g_period_week VARCHAR(1);
DECLARE g_period_month VARCHAR(1);
DECLARE g_period_quarter VARCHAR(1);
DECLARE g_period_semester VARCHAR(1);
DECLARE g_period_year VARCHAR(1);

SET g_true_value = 'TRUE';
SET g_false_value = 'FALSE';
SET g_time_separator = '/';
SET g_period_day = 'D';
SET g_period_week = 'W';
SET g_period_month = 'M';
SET g_period_quarter = 'Q';
SET g_period_semester = 'S';
SET g_period_year = 'A';
	
SET v_size = LENGTH(TRIM(p_timeval));

        CASE v_size
            WHEN 4 THEN
                #date YYYY format
                SET v_retval = str_to_date(CONCAT(p_timeval, '-01-01'), '%Y-%m-%d');
            WHEN 10 THEN
                #date YYYY-MM-DD format
                SET v_retval = str_to_date(p_timeval, '%Y-%m-%d');
            ELSE
                IF v_size < 5 THEN
                    SET v_retval = null;
                ELSE
                    SET v_position = INSTR(p_timeval, g_time_separator);
                    
                    IF v_position <> 0 THEN
                        #YYYY-MM/YYYY-MM format
                        SET v_retval = str_to_date(CONCAT(substr(p_timeval, 1, v_position -1) , '-01'), '%Y-%m-%d');
                    ELSE
                       SET v_retval = vtl_cast_to_date(p_timeval, 'TIME_PERIOD', 'START');
                    END IF;
                END IF;
        END CASE;
		return v_retval;
END

|||

CREATE DEFINER=`SV_VTL_SQL`@`%` PROCEDURE `SV_VTL_SQL`.`vtl_fill_time_series`(IN p_input_dataset VARCHAR(1000),IN p_id_list VARCHAR(1000),IN p_id_time VARCHAR(1000),IN p_limits_method VARCHAR(1000))
    DETERMINISTIC
BEGIN

    DECLARE v_input_dataset VARCHAR(1000); 
    DECLARE v_id_list VARCHAR(1000);
    DECLARE v_id_time VARCHAR(1000); 
    DECLARE v_limits_method VARCHAR(1000); 	
    DECLARE v_if_exists_cycle_tab int; 
    DECLARE v_if_exists_dsr_tab int;
    DECLARE v_min_all_time DATE;
    DECLARE v_max_all_time DATE;
    DECLARE v_min_time VARCHAR(30);
    DECLARE v_max_time VARCHAR(30);
    DECLARE v_period VARCHAR(10);
    DECLARE v_loop_time_1 VARCHAR(100);
    DECLARE v_loop_time_2 VARCHAR(100);
    DECLARE v_loop_row int;
    DECLARE v_loop_row_end int;    
    DECLARE v_cycle_tab_name VARCHAR(50);
    DECLARE v_dsr_tab_name VARCHAR(50);   
    DECLARE v_sql_stmt_single VARCHAR(2000);
   	DECLARE v_sql_stmt_all VARCHAR(2000);  	
   	DECLARE v_stmt_loop_row_end VARCHAR(2000);
   	DECLARE v_stmt_create_dsr VARCHAR(2000);
   
	SET v_input_dataset = p_input_dataset; 
    SET v_id_list = p_id_list;  
    SET v_id_time = p_id_time;
    SET v_limits_method = p_limits_method;
    SET v_loop_row = 1;
    SET v_cycle_tab_name = 'TEMPORARY_FTS_CYCLE';
	SET v_dsr_tab_name = 'TEMPORARY_FTS';
	SET v_sql_stmt_single =
    CONCAT('CREATE TABLE ',v_cycle_tab_name,' AS 
    SELECT DISTINCT '
                 ,v_id_list,', ',
                 'VTL_PERIOD_INDICATOR(',v_id_time,') AS PERIOD, 
                 MAX(',v_id_time,') AS MAX_TIME, 
                 MIN(',v_id_time,') AS MIN_TIME,
                 DENSE_RANK() OVER (ORDER BY ',v_id_list,', VTL_PERIOD_INDICATOR(',v_id_time,')) AS N_ROW
    FROM ',v_input_dataset,
    ' GROUP BY ' ,v_id_list,
                      ' , VTL_PERIOD_INDICATOR(',v_id_time,')',
    ' ORDER BY ' ,v_id_list,
                      ' , VTL_PERIOD_INDICATOR(',v_id_time,')');

    SET v_sql_stmt_all =
    CONCAT('CREATE TABLE ',v_cycle_tab_name,' AS 
    SELECT DISTINCT '
                 ,v_id_list,', ',
                 'VTL_PERIOD_INDICATOR(',v_id_time,') AS PERIOD, 
                 MIN(',v_id_time,') AS MIN_TIME,
                 DENSE_RANK() OVER (ORDER BY ',v_id_list,', VTL_PERIOD_INDICATOR(',v_id_time,')) AS N_ROW
    FROM ',v_input_dataset,
    ' GROUP BY ' ,v_id_list,
                      ' , VTL_PERIOD_INDICATOR(',v_id_time,')',
    ' ORDER BY ' ,v_id_list,
                      ' , VTL_PERIOD_INDICATOR(',v_id_time,')');

    
    SET v_stmt_loop_row_end = CONCAT('SELECT COUNT(*) FROM ',v_cycle_tab_name,' into @outvar');

    SET v_stmt_create_dsr = 
    CONCAT('CREATE TABLE ',v_dsr_tab_name,' AS  
     SELECT DISTINCT '
                 ,v_id_list,', ' 
                 ,v_id_time,' 
     FROM ',v_input_dataset,' 
      LIMIT 0');
            
    
    	SET @sql := CONCAT('SELECT COUNT(*) FROM information_schema.tables WHERE TABLE_NAME = ''',v_cycle_tab_name,''' into @outvar');
        PREPARE stmt FROM @sql;
        EXECUTE stmt;
       	SET v_if_exists_cycle_tab = @outvar;
      	DEALLOCATE PREPARE stmt;
        
        
        SET @sql := CONCAT('SELECT COUNT(*) FROM information_schema.tables WHERE TABLE_NAME = ''',v_dsr_tab_name,''' into @outvar');
        PREPARE stmt FROM @sql;
        EXECUTE stmt;
       	SET v_if_exists_dsr_tab = @outvar;
      	DEALLOCATE PREPARE stmt;
        
        
        IF v_if_exists_cycle_tab=1
        THEN
        	SET @sql := CONCAT('DROP TABLE ',v_cycle_tab_name);
        	PREPARE stmt FROM @sql;
        	EXECUTE stmt;
       		DEALLOCATE PREPARE stmt;
        END IF;

        
        IF v_if_exists_dsr_tab=1
        THEN
            SET @sql := CONCAT('DROP TABLE ',v_dsr_tab_name);
        	PREPARE stmt FROM @sql;
        	EXECUTE stmt;
       		DEALLOCATE PREPARE stmt;
        END IF;

           	SET @sql := v_stmt_create_dsr;
     		PREPARE stmt FROM @sql;
     		EXECUTE stmt ;
       		DEALLOCATE PREPARE stmt;
       	
        IF v_limits_method = 'SINGLE'
        THEN
        	SET @sql := v_sql_stmt_single;
        	PREPARE stmt FROM @sql;
        	EXECUTE stmt;
       		DEALLOCATE PREPARE stmt;
            
            SET @sql := v_stmt_loop_row_end;
        	PREPARE stmt FROM @sql;
        	EXECUTE stmt;
        	SET v_loop_row_end = @outvar;
       		DEALLOCATE PREPARE stmt;
       		
            WHILE v_loop_row <= v_loop_row_end
            DO
            
            	SET @sql := CONCAT('SELECT MIN_TIME FROM ',v_cycle_tab_name,' WHERE N_ROW = ',v_loop_row,' into @outvar');
        		PREPARE stmt FROM @sql;
        		EXECUTE stmt;
        		SET v_min_time = @outvar;
       			DEALLOCATE PREPARE stmt;
                

				SET @sql := CONCAT('SELECT MAX_TIME FROM ',v_cycle_tab_name,' WHERE N_ROW = ',v_loop_row,' into @outvar');
        		PREPARE stmt FROM @sql;
        		EXECUTE stmt;
        		SET v_max_time = @outvar;
       			DEALLOCATE PREPARE stmt;
                
				SET v_loop_time_1 = VTL_TIMESHIFT(v_min_time, -1); 
            	SET v_loop_time_2 = VTL_TIMESHIFT(v_min_time, -1);
    
                WHILE v_loop_time_2 <> v_max_time 
                DO
                    SET v_loop_time_2 = VTL_TIMESHIFT(v_loop_time_1, 1);
        
            		SET @sql := CONCAT('INSERT INTO ',v_dsr_tab_name,' 
                                                          SELECT ',v_id_list,', ','''',v_loop_time_2,'''','  
                                                          FROM ',v_cycle_tab_name,' 
                                                          WHERE N_ROW =', v_loop_row);
        			PREPARE stmt FROM @sql;
        			EXECUTE stmt;
        			DEALLOCATE PREPARE stmt;
                                        
                    COMMIT;
        
                    SET v_loop_time_1 = v_loop_time_2;
                END WHILE;
    
                SET v_loop_row = v_loop_row + 1;                
            END WHILE;            
        ELSE
        	SET @sql := CONCAT('SELECT MIN(convert_to_date(',v_id_time,')) FROM ',v_input_dataset,' into @outvar');
        	PREPARE stmt FROM @sql;
        	EXECUTE stmt;
        	SET v_min_all_time = @outvar;
       		DEALLOCATE PREPARE stmt;
            
       		SET @sql := CONCAT('SELECT MAX(convert_to_date(',v_id_time,')) FROM ',v_input_dataset,' into @outvar');
        	PREPARE stmt FROM @sql;
        	EXECUTE stmt;
        	SET v_max_all_time = @outvar;
       		DEALLOCATE PREPARE stmt;
            
       		SET @sql := v_sql_stmt_all;
        	PREPARE stmt FROM @sql;
        	EXECUTE stmt;
        	DEALLOCATE PREPARE stmt;
            
            SET @sql := v_stmt_loop_row_end;
        	PREPARE stmt FROM @sql;
        	EXECUTE stmt;
        	SET v_loop_row_end = @outvar;
       		DEALLOCATE PREPARE stmt;
            
            WHILE v_loop_row <= v_loop_row_end
            DO
            
            	SET @sql := CONCAT('SELECT MIN_TIME FROM ',v_cycle_tab_name,' WHERE N_ROW = ', v_loop_row ,' into @outvar');
        		PREPARE stmt FROM @sql;
        		EXECUTE stmt;
        		SET v_min_time = @outvar;
       			DEALLOCATE PREPARE stmt;

           		SET @sql := CONCAT('SELECT PERIOD FROM ',v_cycle_tab_name,' WHERE N_ROW = ', v_loop_row ,' into @outvar');
        		PREPARE stmt FROM @sql;
        		EXECUTE stmt;
        		SET v_period = @outvar;
       			DEALLOCATE PREPARE stmt;

                #IF v_period = g_period_date_time THEN
                    SET v_min_time = convert_from_date(v_min_all_time, v_period, v_min_time);

                    SET v_max_time = convert_from_date(v_max_all_time, v_period, v_min_time);                   
                #ELSE
                #    v_min_time = convert_from_date(v_min_all_time, v_period, null);

                #    v_max_time = convert_from_date(v_max_all_time, v_period, null);
                #END IF;

                SET v_loop_time_1 = VTL_TIMESHIFT(v_min_time, -1); 
                SET v_loop_time_2 = VTL_TIMESHIFT(v_min_time, -1);
    
                WHILE (v_loop_time_2 <> v_max_time and v_loop_time_2 < v_max_time)
                DO
                    SET v_loop_time_2 = VTL_TIMESHIFT(v_loop_time_1, 1);
        
            		SET @sql := CONCAT('INSERT INTO ',v_dsr_tab_name,' 
                                                          SELECT ',v_id_list,', ','''',v_loop_time_2,'''','  
                                                          FROM ',v_cycle_tab_name,' 
                                                          WHERE N_ROW = ',v_loop_row);
        			PREPARE stmt FROM @sql;
        			EXECUTE stmt;
        			DEALLOCATE PREPARE stmt;
                    
                    COMMIT;
        
                    SET v_loop_time_1 = v_loop_time_2;
                END WHILE;
    
                SET v_loop_row = v_loop_row + 1;                
            END WHILE;               
        END IF;
                
END

|||