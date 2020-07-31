CREATE FUNCTION dbo.vtl_and_bool(@p_left VARCHAR(5), @p_right VARCHAR(5))
 RETURNS VARCHAR(5)
AS 
BEGIN
	DECLARE @g_true_value VARCHAR(4);
	DECLARE @g_false_value VARCHAR(5);
	DECLARE @v_left_bool bit;
	DECLARE @v_right_bool bit;
	DECLARE @v_res_bool bit;
	DECLARE @v_retval VARCHAR(10);
	SET @g_true_value = 'TRUE';
	SET @g_false_value = 'FALSE';


	IF (@p_left IS NULL) 
		SET @v_left_bool = null;
	ELSE
		SET @v_left_bool = case when @p_left = @g_true_value then 1 else 0 end;
	IF (@p_right IS null)
		SET @v_right_bool = null;
	ELSE
		SET @v_right_bool = case when @p_right = @g_true_value then 1 else 0 end;
		SET @v_res_bool = @v_left_bool & @v_right_bool;
		IF (@v_res_bool IS NULL) 
        	SET @v_retval = null;
        ELSE
            SET @v_retval = case when @v_res_bool = 1 then @g_true_value else @g_false_value end;
    RETURN @v_retval
END;

------------------------------------------------------------------------

CREATE FUNCTION dbo.vtl_is_number(@p_param VARCHAR(20))
RETURNS bit
AS 
BEGIN
	RETURN ISNUMERIC(@p_param)
END;

------------------------------------------------------------------------

CREATE FUNCTION dbo.vtl_iso_week_to_date(@p_iso_year INTEGER, @p_iso_week INTEGER)
 RETURNS DATE
AS 
BEGIN
	DECLARE @v_jan4_of_iso_year DATE;
	DECLARE @v_first_day_of_iso_year DATE;
	DECLARE @v_iso_date DATE;
	DECLARE @v_iso_date_iso_year INTEGER;
	
	SET @v_jan4_of_iso_year = CONVERT(DATE, CONCAT(@p_iso_year, '-01-01'), 102);
    --SET @v_first_day_of_iso_year = @v_jan4_of_iso_year;--TRUNC(v_jan4_of_iso_year, 'IW'); DA VERIFICARE
	
    SET @v_first_day_of_iso_year = case when datepart(dw, @v_jan4_of_iso_year) <= 4 then
   		dateadd(d, 1 - datepart(dw, @v_jan4_of_iso_year), @v_jan4_of_iso_year) else
   		dateadd(d, 8 - datepart(dw, @v_jan4_of_iso_year), @v_jan4_of_iso_year) end
    -- Add the ISO week (in days)
    SET @v_iso_date = DATEADD(day,7 * (@p_iso_week - 1), @v_first_day_of_iso_year);

    -- Check whether iso_week is a valid ISO week
    -- (= whether the Thursday of the week containing iso_date is contained in the year iso_year)
    -- SET @v_iso_date_iso_year = CONVERT(VARCHAR(4),@v_iso_date, 120);
   
   	/*IF @v_iso_date_iso_year > @p_iso_year
   		RETURN null;*/
	
    RETURN @v_iso_date   	 
END;

------------------------------------------------------------------------

CREATE FUNCTION dbo.vtl_not_bool(@p_param VARCHAR(5))
 RETURNS VARCHAR(5)
AS 
BEGIN
	DECLARE @g_true_value VARCHAR(4);
	DECLARE @g_false_value VARCHAR(5);
	DECLARE @v_retval VARCHAR(10);
	SET @g_true_value = 'TRUE';
	SET @g_false_value = 'FALSE';

	IF (@p_param IS NULL) 
		SET @v_retval = null
	ELSE
        IF (@p_param = @g_true_value) 
           	SET @v_retval = @g_false_value
        ELSE
        	SET @v_retval = @g_true_value;

	RETURN @v_retval
END;

------------------------------------------------------------------------

CREATE FUNCTION dbo.vtl_or_bool(@p_left VARCHAR(5), @p_right VARCHAR(5))
 RETURNS VARCHAR(5)
AS 
BEGIN
	DECLARE @g_true_value VARCHAR(4);
	DECLARE @g_false_value VARCHAR(5);
	DECLARE @v_left_bool bit;
	DECLARE @v_right_bool bit;
	DECLARE @v_res_bool bit;
	DECLARE @v_retval VARCHAR(10);
	SET @g_true_value = 'TRUE';
	SET @g_false_value = 'FALSE';


	IF (@p_left IS NULL) 
		SET @v_left_bool = null;
	ELSE
		SET @v_left_bool = case when @p_left = @g_true_value then 1 else 0 end;
	IF (@p_right IS null)
		SET @v_right_bool = null;
	ELSE
		SET @v_right_bool = case when @p_right = @g_true_value then 1 else 0 end;
		SET @v_res_bool = @v_left_bool | @v_right_bool;
		IF (@v_res_bool IS NULL) 
        	SET @v_retval = null;
        ELSE
            SET @v_retval = case when @v_res_bool = 1 then @g_true_value else @g_false_value end;
    RETURN @v_retval
END;

------------------------------------------------------------------------

CREATE FUNCTION dbo.vtl_period_indicator(@p_timeval VARCHAR(10))
 RETURNS VARCHAR(20)
AS 
BEGIN
	
DECLARE @g_time_separator VARCHAR(1);
DECLARE @g_period_day VARCHAR(1);
DECLARE @g_period_week VARCHAR(1);
DECLARE @g_period_month VARCHAR(1);
DECLARE @g_period_quarter VARCHAR(1);
DECLARE @g_period_semester VARCHAR(1);
DECLARE @g_period_year VARCHAR(1);
DECLARE @g_period_date_time VARCHAR(1);
DECLARE @v_size INTEGER;
DECLARE @v_period VARCHAR(1);
DECLARE @v_retval VARCHAR(50);
DECLARE @Op VARCHAR(15)
SET @g_time_separator = '/';
SET @g_period_day = 'D';
SET @g_period_week = 'W';
SET @g_period_month = 'M';
SET @g_period_quarter = 'Q';
SET @g_period_semester = 'S';
SET @g_period_year = 'A';
SET @g_period_date_time = 'T';
SET @v_size = LEN(@p_timeval);

IF (@v_size = 4)
	SET @v_retval = @g_period_year;
ELSE IF (@v_size = 10)
	SET @v_retval = @g_period_date_time;
ELSE
	BEGIN
	IF (@v_size < 5)
    	SET @v_retval = 'Invalid format';
    ELSE
    	BEGIN
    	SET @v_period = SUBSTRING(@p_timeval, 5, 1);
		IF (@v_period = '-')
			BEGIN
    		SET @v_period = SUBSTRING(@p_timeval, 6, 1);
			IF (ISNUMERIC(@v_period) = 1)
    			SET @v_retval = @g_period_month;
    		ELSE
    			BEGIN
    			IF (@v_period = @g_period_day or @v_period = @g_period_week 
                	OR @v_period = @g_period_month or @v_period = @g_period_quarter 
                    OR @v_period = @g_period_semester or @v_period = @g_period_year)
        			SET @v_retval = @v_period;
        		ELSE
        			SET @v_retval = 'Invalid format';
        		END
        	END
		ELSE
			BEGIN
        --other supported format
     		IF (ISNUMERIC(@v_period) = 1)
     			SET @v_retval = @g_period_month;
     		ELSE
     			IF (@v_period = @g_period_day or @v_period = @g_period_week 
                	OR @v_period = @g_period_month or @v_period = @g_period_quarter 
                    OR @v_period = @g_period_semester or @v_period = @g_period_year)
     				SET @v_retval = @v_period;
        		ELSE
        			SET @v_retval = 'Invalid format';
        	END
		END
	END
	
RETURN @v_retval;
END

------------------------------------------------------------------------

CREATE FUNCTION dbo.vtl_xor_bool(@p_left VARCHAR(5), @p_right VARCHAR(5))
 RETURNS VARCHAR(5)
AS 
BEGIN
	DECLARE @g_true_value VARCHAR(4);
	DECLARE @g_false_value VARCHAR(5);
	DECLARE @v_left_bool bit;
	DECLARE @v_right_bool bit;
	DECLARE @v_res_bool bit;
	DECLARE @v_retval VARCHAR(10);
	SET @g_true_value = 'TRUE';
	SET @g_false_value = 'FALSE';


	IF (@p_left IS NULL OR @p_right IS NULL) 
		SET @v_retval = null;
	ELSE
		IF (@p_left = @p_right) 
        	SET @v_retval = @g_false_value;
        ELSE
            SET @v_retval = @g_true_value;
	
    RETURN @v_retval
END;

------------------------------------------------------------------------

CREATE FUNCTION dbo.vtl_timeshift(@p_timeval VARCHAR(20), @p_shiftval INTEGER)
RETURNS VARCHAR(20)
AS 
BEGIN

DECLARE @g_time_separator VARCHAR(1);
DECLARE @g_period_day VARCHAR(1);
DECLARE @g_period_week VARCHAR(1);
DECLARE @g_period_month VARCHAR(1);
DECLARE @g_period_quarter VARCHAR(1);
DECLARE @g_period_semester VARCHAR(1);
DECLARE @g_period_year VARCHAR(1);
DECLARE @v_size INTEGER;
DECLARE @v_position INTEGER;
DECLARE @v_period VARCHAR(1);
DECLARE @v_leftval VARCHAR(50);
DECLARE @v_rightval VARCHAR(50);
DECLARE @v_retval VARCHAR(50);
SET @g_time_separator = '/';
SET @g_period_day = 'D';
SET @g_period_week = 'W';
SET @g_period_month = 'M';
SET @g_period_quarter = 'Q';
SET @g_period_semester = 'S';
SET @g_period_year = 'A';

SET @v_size = LEN(@p_timeval);

IF(@v_size = 4)
    SET @v_retval = @p_timeval + @p_shiftval;
ELSE 
	IF(@v_size = 10)
	SET @v_retval = CONVERT(varchar, DATEADD(year, @p_shiftval , CAST(@p_timeval as date)));
ELSE 
	BEGIN
	IF(@v_size < 5)
		SET @v_retval = 'Invalid format';
	ELSE
		BEGIN
		SET @v_position = CHARINDEX(@g_time_separator, @p_timeval);
    	IF (@v_position <> 0)
    		BEGIN
    		SET @v_leftval = dbo.vtl_timeshift(SUBSTRING(@p_timeval, 1, @v_position - 1), @p_shiftval * 12);
        	SET @v_rightval = dbo.vtl_timeshift(SUBSTRING(@p_timeval, @v_position + 1, LEN(@p_timeval)), @p_shiftval * 12);
        	SET @v_retval = CONCAT(@v_leftval, @g_time_separator, @v_rightval);
			END
        ELSE
        	BEGIN
    		SET @v_period = SUBSTRING(@p_timeval, 5, 1);
			IF (@v_period = '-')
				BEGIN
        		SET @v_period = SUBSTRING(@p_timeval, 6, 1);
        		--switch1
        		IF (@v_period = @g_period_day)
                	--date YYYY-Dddd format OK
                	BEGIN
                	SET @v_retval = dateadd(day, @p_shiftval + cast(right(@p_timeval,3) as int)-1, cast(left(@p_timeval,4)+'0101' as date));
                    SET @v_retval = CONCAT(SUBSTRING(@v_retval, 1, 4), '-', @g_period_day, REPLACE(STR(DATEPART(dy ,@v_retval), 3),' ','0'))--);
                   	END
                ELSE 
                	IF (@v_period = @g_period_week)
                	BEGIN
                	--date YYYY-Www format OK
                	SET @v_retval = dateadd(day, (@p_shiftval * 7) - 1 ,dbo.vtl_iso_week_to_date(SUBSTRING(@p_timeval, 1, 4), SUBSTRING(@p_timeval, 7, 2)));
                    SET @v_retval = CONCAT(SUBSTRING(@v_retval, 1, 4), '-', @g_period_week, REPLACE(STR(DATEPART(ww ,@v_retval), 2),' ','0'));
               		END
                ELSE 
                	IF (@v_period = @g_period_month)
                	BEGIN
                	--period YYYY-Mmm format OK
                	SET @v_retval = dateadd(month, @p_shiftval ,CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), SUBSTRING(@p_timeval, 7, 2), '01') as date))
                	SET @v_retval = CONCAT(SUBSTRING(@v_retval, 1, 4) , '-' , @g_period_month , REPLACE(STR(DATEPART(mm ,@v_retval), 2),' ','0'));
                	END
                ELSE 
                	IF (@v_period = @g_period_quarter)
                	BEGIN
                	--date YYYY-Qq format 
                	SET @v_retval = dateadd(month, @p_shiftval * 3 ,CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4),REPLACE(STR(SUBSTRING(@p_timeval, 7, 1)*3, 2),' ','0'), '01') as date))
   					SET @v_retval = CONCAT(SUBSTRING(@v_retval, 1, 4), '-', @g_period_quarter, DATEPART(qq,@v_retval));
                	END
   				ELSE IF (@v_period = @g_period_semester)
   					BEGIN
                	--date YYYY-Ss format
                	SET @v_retval = dateadd(month, @p_shiftval * 6 ,CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4),REPLACE(STR(SUBSTRING(@p_timeval, 7, 1)*6, 2),' ','0'), '01') as date))
                	SET @v_retval = CONCAT(SUBSTRING(@v_retval, 1, 4), '-', @g_period_semester, CEILING(CAST(SUBSTRING(@v_retval, 6, 2)as float) / 6.0));
                	END
                ELSE IF (@v_period = @g_period_year)		
                	--period YYYY-A format OK
                    SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4) + @p_shiftval , '-' , @g_period_year);
                ELSE
                	BEGIN
                	IF (@v_size = 7)
                		BEGIN
                		--date YYYY-mm format OK
                		SET @v_retval = dateadd(month, @p_shiftval ,CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), SUBSTRING(@p_timeval, 6, 2), '01') as date))
                		SET @v_retval = CONCAT(SUBSTRING(@v_retval, 1, 4) , '-' ,SUBSTRING(@v_retval, 6, 2));
                		END
                	ELSE
                    	BEGIN
                    	IF (@v_size = 8)
                        	--date YYYY-xMM format
                        	BEGIN
                        	SET @v_retval = dateadd(month, @p_shiftval ,CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), SUBSTRING(@p_timeval, 7, 2), '01') as date))
                			SET @v_retval = CONCAT(SUBSTRING(@v_retval, 1, 4), SUBSTRING(@p_timeval, 5, 2), SUBSTRING(@v_retval, 6, 2)); 
                    		END
                        ELSE
                        	SET @v_retval = 'Invalid format';
                        END
                    END   
                --switch1
        		END
			ELSE
				BEGIN
				--switch2
				IF (@v_period = @g_period_day)
                	--date YYYYDddd format OK
                	BEGIN
                	SET @v_retval = dateadd(day, @p_shiftval + cast(right(@p_timeval,3) as int)-1, cast(left(@p_timeval,4)+'0101' as date));
                    SET @v_retval = CONCAT(SUBSTRING(@v_retval, 1, 4), @g_period_day, REPLACE(STR(DATEPART(dy ,@v_retval), 3),' ','0'));
                   	END
                ELSE 
                	IF (@v_period = @g_period_week)
                	BEGIN
                	--date YYYYWww format OK
                	SET @v_retval = dateadd(day, (@p_shiftval * 7) - 1 ,dbo.vtl_iso_week_to_date(SUBSTRING(@p_timeval, 1, 4), SUBSTRING(@p_timeval, 6, 2)));
                    SET @v_retval = CONCAT(SUBSTRING(@v_retval, 1, 4), @g_period_week, REPLACE(STR(DATEPART(ww ,@v_retval), 2),' ','0'));
               		END
                ELSE 
                	IF (@v_period = @g_period_month)
                	BEGIN
                	--period YYYYMmm format OK
                	SET @v_retval = dateadd(month, @p_shiftval ,CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), SUBSTRING(@p_timeval, 6, 2), '01') as date))
                	SET @v_retval = CONCAT(SUBSTRING(@v_retval, 1, 4), @g_period_month , REPLACE(STR(DATEPART(mm ,@v_retval), 2),' ','0'));
                	END
                ELSE 
                	IF (@v_period = @g_period_quarter)
                	BEGIN
                	--date YYYYQq format 
                	SET @v_retval = dateadd(month, @p_shiftval * 3 ,CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4),REPLACE(STR(SUBSTRING(@p_timeval, 6, 1)*3, 2),' ','0'), '01') as date))
   					SET @v_retval = CONCAT(SUBSTRING(@v_retval, 1, 4), @g_period_quarter, DATEPART(qq,@v_retval));
                	END
   				ELSE IF (@v_period = @g_period_semester)
   					BEGIN
                	--date YYYYSs format
                	SET @v_retval = dateadd(month, @p_shiftval * 6 ,CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4),REPLACE(STR(SUBSTRING(@p_timeval, 6, 1)*6, 2),' ','0'), '01') as date))
                	SET @v_retval = CONCAT(SUBSTRING(@v_retval, 1, 4), @g_period_semester, CEILING(CAST(SUBSTRING(@v_retval, 6, 2)as float) / 6.0));
                	END
                ELSE IF (@v_period = @g_period_year)		
                	--period YYYYA format OK
                    SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4) + @p_shiftval , @g_period_year);
                ELSE
                	BEGIN
                	IF (@v_size = 6)
                		BEGIN
                		--date YYYYmm format OK
                		SET @v_retval = dateadd(month, @p_shiftval ,CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), SUBSTRING(@p_timeval, 5, 2), '01') as date))
                		SET @v_retval = CONCAT(SUBSTRING(@v_retval, 1, 4) ,SUBSTRING(@v_retval, 6, 2));
                		END
                	ELSE
                    	BEGIN
                    	IF (@v_size = 7)
                        	--date YYYYxMM format
                        	BEGIN
                        	SET @v_retval = dateadd(month, @p_shiftval ,CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), SUBSTRING(@p_timeval, 6, 2), '01') as date))
                			SET @v_retval = CONCAT(SUBSTRING(@v_retval, 1, 4), SUBSTRING(@p_timeval, 5, 1), SUBSTRING(@v_retval, 6, 2)); 
                    		END
                        ELSE
                        	SET @v_retval = 'Invalid format';
                        END
                    END  
				--switch2
				END
			END
		END
	END
RETURN @v_retval;
END

------------------------------------------------------------------------

CREATE FUNCTION dbo.vtl_time_aggv2(@p_timeval DATE, @p_period_to VARCHAR(50), @p_period_from VARCHAR(50), @p_start VARCHAR(50))
RETURNS DATETIME
AS 
BEGIN
	DECLARE @v_retval DATE;
	SET @v_retval = CONVERT(DATE, dbo.vtl_time_agg(CONVERT(varchar, @p_timeval), @p_period_to, @p_period_from, @p_start));
	RETURN @v_retval;
END

------------------------------------------------------------------------

CREATE FUNCTION dbo.vtl_time_agg(@p_timeval VARCHAR(50), @p_period_to VARCHAR(50), @p_period_from VARCHAR(50), @p_start VARCHAR(50))
RETURNS VARCHAR(50)
AS 
BEGIN
	
DECLARE @g_true_value VARCHAR(4);
DECLARE @g_false_value VARCHAR(5);
DECLARE @g_time_separator VARCHAR(1);
DECLARE @g_period_day VARCHAR(1);
DECLARE @g_period_week VARCHAR(1);
DECLARE @g_period_month VARCHAR(1);
DECLARE @g_period_quarter VARCHAR(1);
DECLARE @g_period_semester VARCHAR(1);
DECLARE @g_period_year VARCHAR(1);
DECLARE @v_size INTEGER;
DECLARE @v_position INTEGER;
DECLARE @v_period VARCHAR(1);
DECLARE @v_start VARCHAR(10);
DECLARE @v_retval VARCHAR(50);

SET @g_true_value = 'TRUE';
SET @g_false_value = 'FALSE';
SET @g_time_separator = '/';
SET @g_period_day = 'D';
SET @g_period_week = 'W';
SET @g_period_month = 'M';
SET @g_period_quarter = 'Q';
SET @g_period_semester = 'S';
SET @g_period_year = 'A';


IF (@p_timeval = 'NULL' )
	RETURN NULL
        
SET @v_start = coalesce(@p_start, 'first');
        
SET @v_size = LEN(@p_timeval);


IF ( @v_size = 4 )
    --date YYYY format
    SET @v_retval = @p_timeval;
ELSE IF ( @v_size = 10 )
	BEGIN
    --date YYYY-%m-DD format
    	
        	IF ( @p_period_to = @g_period_day )
            	SET @v_retval = @p_timeval;
        	ELSE 
        		IF ( @p_period_to = @g_period_week )
        		BEGIN
            	IF (@v_start = 'first' )
            		SET @v_retval = CONVERT(varchar, dateadd(d, 1 - datepart(dw, CONVERT(DATE, @p_timeval)), CONVERT(DATE, @p_timeval)));
            	ELSE
            		SET @v_retval = CONVERT(varchar, dateadd(d, 7 - datepart(dw, CONVERT(DATE, @p_timeval)), CONVERT(DATE, @p_timeval)));
                END
        	ELSE
        		IF ( @p_period_to = @g_period_month )
        		BEGIN
            	IF (@v_start = 'first' )
            		SET @v_retval = CONVERT(varchar, DATEADD(DAY,1,EOMONTH(@p_timeval,-1)));
            	ELSE
            		SET @v_retval = CONVERT(varchar, EOMONTH(@p_timeval));
                END
            ELSE 
            	IF ( @p_period_to = @g_period_quarter )
            	BEGIN
	            IF (SUBSTRING(@p_timeval, 6, 2) <= 3 )
            		BEGIN
                		IF (@v_start = 'first' )
                    		SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), '-01-01');
                		ELSE
                    		SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), '-03-31');
                    END
            	ELSE
            		IF (SUBSTRING(@p_timeval, 6, 2) <= 6 )
                	BEGIN
            			IF (@v_start = 'first' )
                    		SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), '-04-01');
                		ELSE
                    		SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), '-06-30');
                    END
            	ELSE
            		IF (SUBSTRING(@p_timeval, 6, 2) <= 9 )
                	BEGIN
            			IF (@v_start = 'first' )
                    		SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), '-07-01');
                		ELSE
                    		SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), '-09-30');
                    END
            	ELSE
            		BEGIN
                		IF (@v_start = 'first' )
                    		SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), '-10-01');
                		ELSE
                    		SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), '-12-31');
                    END 
                END
			ELSE
				IF ( @p_period_to = @g_period_semester )
            	BEGIN
				IF (SUBSTRING(@p_timeval, 6, 2) <= 6 )
					BEGIN
                	IF (@v_start = 'first' )
                    	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), '-01-01');
                	ELSE
                    	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), '-06-30');
                    END
            	ELSE
            		BEGIN
                	IF (@v_start = 'first' )
                    	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), '-07-01');
                    ELSE
                    	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), '-12-31');
                    END
                END 
			ELSE 
				IF ( @p_period_to = @g_period_year )
				BEGIN
            	IF (@v_start = 'first' )
                	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), '-01-01');
                ELSE
                	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), '-12-31');
                END
            ELSE 
            	SET @v_retval = @p_timeval;
	END --IF ( @v_size = 10 )
ELSE 
	BEGIN --1lv
		IF (@v_size < 5 )
        	SET @v_retval = 'Invalid format';
    	ELSE
    		BEGIN --lv2
        	SET @v_position = CHARINDEX(@g_time_separator, @p_timeval);
            IF (@v_position <> 0 )
            --YYYY-%m/YYYY-%m format
            	SET @v_retval = 'Invalid format';
        	ELSE
        		BEGIN --lv3
            	SET @v_period = SUBSTRING(@p_timeval, 5, 1);

				IF (@v_period = '-' )
					BEGIN --lv 4-1
                	SET @v_period = SUBSTRING(@p_timeval, 6, 1);
                        	--switch
                            IF ( @v_period = @g_period_day )
                            	BEGIN
                                --YYYY-Dddd format
                                IF (@p_period_to = @g_period_year )
                                    SET @v_retval = SUBSTRING(@p_timeval, 1, 4);
                                ELSE
                                	IF (@p_period_to = @g_period_semester )
                                	BEGIN
                                    	IF (SUBSTRING(@p_timeval, 7, 3) <= CONVERT(INTEGER, DATEPART(dy, CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), '0630') as DATE))) )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '1');
                                    	ELSE
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '2');
                                    END
                                ELSE
                                	IF (@p_period_to = @g_period_quarter )
                                    BEGIN
                                		IF (SUBSTRING(@p_timeval, 7, 3) <= CONVERT(INTEGER, DATEPART(dy, CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), '0331') as DATE))) )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '1');
                                    	ELSE IF (SUBSTRING(@p_timeval, 7, 3) <= CONVERT(INTEGER, DATEPART(dy, CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), '0630') as DATE))) )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '2');
                                    	ELSE IF (SUBSTRING(@p_timeval, 7, 3) <= CONVERT(INTEGER, DATEPART(dy, CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), '0930') as DATE))) )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '3');
                                    	ELSE
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '4');
                                    END
                                ELSE IF (@p_period_to = @g_period_month )
                                	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_month, REPLACE(STR(DATEPART(mm, dateadd(d,SUBSTRING(@p_timeval, 7, 3) -1 ,CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), '0101') as DATE))), 2),' ','0'));
                                ELSE IF (@p_period_to = @g_period_week )
                                	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_week, REPLACE(STR(DATEPART(ISO_WEEK, dateadd(d,SUBSTRING(@p_timeval, 7, 3) -1 ,CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), '0101') as DATE))), 2),' ','0'));
          						ELSE
                                    SET @v_retval = @p_timeval;
                                END
            				ELSE 
            					IF ( @v_period = @g_period_week )
            					BEGIN
                                --YYYY-Www format
                                IF (@p_period_to = @g_period_year )
                                    SET @v_retval = SUBSTRING(@p_timeval, 1, 4);
                                ELSE
                                	IF (@p_period_to = @g_period_semester )
                                    BEGIN
                                		IF (SUBSTRING(@p_timeval, 7, 2) <= CONVERT(INTEGER, DATEPART(ww, CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), '0630') as DATE))) )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '1');
                                    	ELSE
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '2');
                                    END
                                ELSE
                                	IF (@p_period_to = @g_period_quarter )
                                    BEGIN
                                		IF (SUBSTRING(@p_timeval, 7, 2) <= CONVERT(INTEGER, DATEPART(ww, CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), '0331') as DATE))) )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '1');
                                    	ELSE IF (SUBSTRING(@p_timeval, 7, 2) <= CONVERT(INTEGER, DATEPART(ww, CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), '0630') as DATE))) )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '2');
                                    	ELSE IF (SUBSTRING(@p_timeval, 7, 2) <= CONVERT(INTEGER, DATEPART(ww, CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), '0930') as DATE))) )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '3');
                                    	ELSE
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '4');
                                    END
                                ELSE IF (@p_period_to = @g_period_month )
                                    SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_month, REPLACE(STR(DATEPART(mm, CAST(dbo.vtl_iso_week_to_date(SUBSTRING(@p_timeval, 1, 4), SUBSTRING(@p_timeval, 7, 2)) as DATE)),2),' ','0'));
                                ELSE
                                    SET @v_retval = @p_timeval;
                                END
                            ELSE 
                            	IF ( @v_period = @g_period_month )
                            	BEGIN
                                --YYYY-Mmm format
                                IF (@p_period_to = @g_period_year )
                                    SET @v_retval = SUBSTRING(@p_timeval, 1, 4);
                                ELSE 
                                	IF (@p_period_to = @g_period_semester )
                                    BEGIN
                                		IF (SUBSTRING(@p_timeval, 7, 2) <= 6 )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '1');
                                    	ELSE
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '2');
                                    END
                                ELSE
                                	IF (@p_period_to = @g_period_quarter )
                                    BEGIN
                                		IF (SUBSTRING(@p_timeval, 7, 2) <= 3 )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '1');
   										ELSE IF (SUBSTRING(@p_timeval, 7, 2) <= 6 )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '2');
                                    	ELSE IF (SUBSTRING(@p_timeval, 7, 2) <= 9 )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '3');
                                    	ELSE
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '4');
                                    END
                                ELSE
                                    SET @v_retval = @p_timeval;
                                END 
                            ELSE 
                            	IF ( @v_period = @g_period_quarter )
                            	BEGIN
                                --YYYY-Qq format
                                IF (@p_period_to = @g_period_year )
                                    SET @v_retval = SUBSTRING(@p_timeval, 1, 4);
                                ELSE
                                	IF (@p_period_to = @g_period_semester )
                                    BEGIN
                                		IF (SUBSTRING(@p_timeval, 7, 2) <= 2 )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '1');
                                    	ELSE
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '2');
                                    END
                                ELSE
                                    SET @v_retval = @p_timeval;
                                END 
                            ELSE 
                            	IF ( @v_period = @g_period_semester )
                                BEGIN
                            	--YYYY-Ss format
                                IF (@p_period_to = @g_period_year )
                                    --YYYY-xMM format
                                    SET @v_retval = SUBSTRING(@p_timeval, 1, 4);
                                ELSE
                                    SET @v_retval = @p_timeval;
                                END 
                            ELSE 
                            	IF ( @v_period = @g_period_year )
                                --YYYY-A format
                                SET @v_retval = SUBSTRING(@p_timeval, 1, 4);
                            ELSE
                            	BEGIN --else lv5-1
                                IF (@v_size = 7 )
                                	BEGIN --lv 5-2
                                    --YYYY-%m format
                                    IF (@p_period_to = @g_period_year )
                                        SET @v_retval = SUBSTRING(@p_timeval, 1, 4);
                                    ELSE
                                    	IF (@p_period_to = @g_period_semester )
                                        BEGIN
                                    		IF (SUBSTRING(@p_timeval, 6, 2) <= 6 )
                                        	    SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '1');
                                        	ELSE
                                            	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '2');
                                        END
                                    ELSE
                                    	IF (@p_period_to = @g_period_quarter )
                                        BEGIN
                                    		IF (SUBSTRING(@p_timeval, 6, 2) <= 3 )
                                         	   SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '1');
                                        	ELSE IF (SUBSTRING(@p_timeval, 6, 2) <= 6 )
                                            	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '2');
                                        	ELSE IF (SUBSTRING(@p_timeval, 6, 2) <= 9 )
                                            	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '3');
                                        	ELSE
                                            	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '4');
                                        END
                                    ELSE
                                        SET @v_retval = @p_timeval;
                                    
                                    END --if lv 5-2
                                ELSE
                                	BEGIN --else 5-3
                                    IF (@v_size = 8 )
                                    	BEGIN --if 5-4
                                        --YYYY-xMM format
                                        IF (@p_period_to = @g_period_year )
                                            SET @v_retval = SUBSTRING(@p_timeval, 1, 4);
                                        ELSE
                                        	IF (@p_period_to = @g_period_semester )
                                            BEGIN
                                        		IF (SUBSTRING(@p_timeval, 7, 2) <= 6 )
                                                	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '1');
                                            	ELSE
                                                	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '2');
                                            END
                                        ELSE
                                        	IF (@p_period_to = @g_period_quarter )
                                            BEGIN
                                        		IF (SUBSTRING(@p_timeval, 7, 2) <= 3 )
                                                SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '1');
                                            	ELSE IF (SUBSTRING(@p_timeval, 7, 2) <= 6 )
                                                	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '2');
                                            	ELSE IF (SUBSTRING(@p_timeval, 7, 2) <= 9 )
                                                	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '3');
                                            	ELSE
                                                	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '4');
                                            END
                                        ELSE
                                            SET @v_retval = @p_timeval;
                                    	END --if 5-4
                                    ELSE
                                        SET @v_retval = 'Invalid format';
                                	END --else 5-3
                                END --else 5-1  
                    		--end switch  
                	END --if lv 4-1
				ELSE
					BEGIN --lv 4-2
                    		--switch
                            IF ( @v_period = @g_period_day )
                            	BEGIN
                                --YYYYDddd format
                                IF (@p_period_to = @g_period_year )
                                    SET @v_retval = SUBSTRING(@p_timeval, 1, 4);
                                ELSE
                                	IF (@p_period_to = @g_period_semester )
                                	BEGIN
                                    	IF (SUBSTRING(@p_timeval, 6, 3) <= CONVERT(INTEGER, DATEPART(dy, CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), '0630') as DATE))) )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '1');
                                    	ELSE
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '2');
                                    END
                                ELSE
                                	IF (@p_period_to = @g_period_quarter )
                                    BEGIN
                                		IF (SUBSTRING(@p_timeval, 6, 3) <= CONVERT(INTEGER, DATEPART(dy, CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), '0331') as DATE))) )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '1');
                                    	ELSE IF (SUBSTRING(@p_timeval, 6, 3) <= CONVERT(INTEGER, DATEPART(dy, CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), '0630') as DATE))) )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '2');
                                    	ELSE IF (SUBSTRING(@p_timeval, 6, 3) <= CONVERT(INTEGER, DATEPART(dy, CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), '0930') as DATE))) )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '3');
                                    	ELSE
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '4');
                                    END
                                ELSE IF (@p_period_to = @g_period_month )
                                	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_month, REPLACE(STR(DATEPART(mm, dateadd(d,SUBSTRING(@p_timeval, 6, 3) -1 ,CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), '0101') as DATE))), 2),' ','0'));
                                ELSE IF (@p_period_to = @g_period_week )
                                	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_week, REPLACE(STR(DATEPART(ISO_WEEK, dateadd(d,SUBSTRING(@p_timeval, 6, 3) -1 ,CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), '0101') as DATE))), 2),' ','0'));
                                ELSE
                                    SET @v_retval = @p_timeval;
                                END
            				ELSE 
            					IF ( @v_period = @g_period_week )
            					BEGIN
                                --YYYYWww format
                                IF (@p_period_to = @g_period_year )
                                    SET @v_retval = SUBSTRING(@p_timeval, 1, 4);
                                ELSE
                                	IF (@p_period_to = @g_period_semester )
                                    BEGIN
                                		IF (SUBSTRING(@p_timeval, 6, 2) <= CONVERT(INTEGER, DATEPART(ww, CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), '0630') as DATE))) )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '1');
                                    	ELSE
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '2');
                                    END
                                ELSE
                                	IF (@p_period_to = @g_period_quarter )
                                    BEGIN
                                		IF (SUBSTRING(@p_timeval, 6, 2) <= CONVERT(INTEGER, DATEPART(ww, CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), '0331') as DATE))) )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '1');
                                    	ELSE IF (SUBSTRING(@p_timeval, 6, 2) <= CONVERT(INTEGER, DATEPART(ww, CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), '0630') as DATE))) )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '2');
                                    	ELSE IF (SUBSTRING(@p_timeval, 6, 2) <= CONVERT(INTEGER, DATEPART(ww, CAST(CONCAT(SUBSTRING(@p_timeval, 1, 4), '0930') as DATE))) )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '3');
                                    	ELSE
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '4');
                                    END
                                ELSE IF (@p_period_to = @g_period_month )
                                    SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_month, REPLACE(STR(DATEPART(mm, CAST(dbo.vtl_iso_week_to_date(SUBSTRING(@p_timeval, 1, 4), SUBSTRING(@p_timeval, 6, 2)) as DATE)),2),' ','0'));
                                ELSE
                                    SET @v_retval = @p_timeval;
                                END
                            ELSE 
                            	IF ( @v_period = @g_period_month )
                            	BEGIN
                                --YYYYMmm format
                                IF (@p_period_to = @g_period_year )
                                    SET @v_retval = SUBSTRING(@p_timeval, 1, 4);
                                ELSE 
                                	IF (@p_period_to = @g_period_semester )
                                    BEGIN
                                		IF (SUBSTRING(@p_timeval, 6, 2) <= 6 )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '1');
                                    	ELSE
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '2');
                                    END
                                ELSE
                                	IF (@p_period_to = @g_period_quarter )
                                    BEGIN
                                		IF (SUBSTRING(@p_timeval, 6, 2) <= 3 )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '1');
                                    	ELSE IF (SUBSTRING(@p_timeval, 6, 2) <= 6 )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '2');
                                    	ELSE IF (SUBSTRING(@p_timeval, 6, 2) <= 9 )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '3');
                                    	ELSE
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '4');
                                    END
                                ELSE
                                    SET @v_retval = @p_timeval;
                                END 
                            ELSE 
                            	IF ( @v_period = @g_period_quarter )
                            	BEGIN
                                --YYYYQq format
                                IF (@p_period_to = @g_period_year )
                                    SET @v_retval = SUBSTRING(@p_timeval, 1, 4);
                                ELSE
                                	IF (@p_period_to = @g_period_semester )
                                    BEGIN
                                		IF (SUBSTRING(@p_timeval, 6, 2) <= 2 )
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '1');
                                    	ELSE
                                        	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '2');
                                    END
                                ELSE
                                    SET @v_retval = @p_timeval;
                                END 
                            ELSE 
                            	IF ( @v_period = @g_period_semester )
                                BEGIN
               	--YYYYSs format
                                IF (@p_period_to = @g_period_year )
                                    --YYYY-xMM format
                                    SET @v_retval = SUBSTRING(@p_timeval, 1, 4);
                                ELSE
                                    SET @v_retval = @p_timeval;
                                END 
                            ELSE 
                            	IF ( @v_period = @g_period_year )
                                --YYYYA format
                                SET @v_retval = SUBSTRING(@p_timeval, 1, 4);
                            ELSE
                            	BEGIN --else lv5-1
                                IF (@v_size = 6 )
                                	BEGIN --lv 5-2
                                    --YYYY%m format
                                    IF (@p_period_to = @g_period_year )
                                        SET @v_retval = SUBSTRING(@p_timeval, 1, 4);
                                    ELSE
                                    	IF (@p_period_to = @g_period_semester )
                                        BEGIN
                                    		IF (SUBSTRING(@p_timeval, 5, 2) <= 6 )
                                        	    SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '1');
                                        	ELSE
                                            	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '2');
                                        END
                                    ELSE
                                    	IF (@p_period_to = @g_period_quarter )
                                        BEGIN
                                    		IF (SUBSTRING(@p_timeval, 5, 2) <= 3 )
                                         	   SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '1');
                                        	ELSE IF (SUBSTRING(@p_timeval, 5, 2) <= 6 )
                                            	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '2');
                                        	ELSE IF (SUBSTRING(@p_timeval, 5, 2) <= 9 )
                                            	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '3');
                                        	ELSE
                                            	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '4');
                                        END
                                    ELSE
                                        SET @v_retval = @p_timeval;
                                    
                                    END --if lv 5-2
                                ELSE
                                	BEGIN --else 5-3
                                    IF (@v_size = 7 )
                                    	BEGIN --if 5-4
                                        --YYYYxMM format
                                        IF (@p_period_to = @g_period_year )
                                            SET @v_retval = SUBSTRING(@p_timeval, 1, 4);
                                        ELSE
                                        	IF (@p_period_to = @g_period_semester )
                                            BEGIN
                                        		IF (SUBSTRING(@p_timeval, 6, 2) <= 6 )
                                                	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '1');
                                            	ELSE
                                                	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_semester, '2');
                                            END
                                        ELSE
                                        	IF (@p_period_to = @g_period_quarter )
                                            BEGIN
                                        		IF (SUBSTRING(@p_timeval, 6, 2) <= 3 )
                                                SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '1');
                                            	ELSE IF (SUBSTRING(@p_timeval, 6, 2) <= 6 )
                                                	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '2');
                                            	ELSE IF (SUBSTRING(@p_timeval, 6, 2) <= 9 )
                                                	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '3');
                                            	ELSE
                                                	SET @v_retval = CONCAT(SUBSTRING(@p_timeval, 1, 4), @g_period_quarter, '4');
                                            END
                                        ELSE
                                            SET @v_retval = @p_timeval;
                                    	END --if 5-4
                                    ELSE
                                        SET @v_retval = 'Invalid format';
                                	END --else 5-3
                                END --else 5-1  
                    		--end switch 
					END -- else lv  4-2
			END --else lv3  
		END  --else lv2  
	END --else lv1

RETURN @v_retval;
END

------------------------------------------------------------------------

CREATE FUNCTION dbo.vtl_standardize_number(@p_value VARCHAR(50))
 RETURNS FLOAT
AS 
BEGIN
	
DECLARE @v_standard FLOAT; 
DECLARE @v_perc bit;
DECLARE @v_numeratore FLOAT;
DECLARE @v_denominatore FLOAT;
DECLARE @v_power INT;
DECLARE @v_int NUMERIC;

IF CHARINDEX('%',SUBSTRING(@p_value, 1, LEN(@p_value))) = 0 
	SET @v_perc = 0;
ELSE 
	SET @v_perc = 1;

SET @v_int = CHARINDEX('/',REVERSE(REPLACE(REPLACE(@p_value, '.','/'),',','/')))-1
SET @v_numeratore = (REPLACE(REPLACE (REPLACE (@p_value, '%', ''), '.', ''),',',''));  --tolgo '%' e punti e virgole 

IF @v_perc = 1
	BEGIN
	if @v_int > 0
   		SET @v_power = @v_int - 1;
   	else
   		SET @v_power = 0;
   	END
ELSE 
	BEGIN
	if @v_int > 0
    	SET @v_power = @v_int;
    else
    	SET @v_power = 0;
    END

SET @v_denominatore = POWER(10,@v_power); 

IF @v_perc = 1 
	SET @v_standard = @v_numeratore/(100*@v_denominatore);
ELSE 
    SET @v_standard = @v_numeratore / (@v_denominatore);
 
RETURN @v_standard;
END

------------------------------------------------------------------------

CREATE FUNCTION dbo.vtl_cast_to_int(@p_value VARCHAR(50), @p_datatype VARCHAR(50))
 RETURNS INT
AS 
BEGIN
	
DECLARE @v_output_value INT;
DECLARE @g_true_value VARCHAR(4);
DECLARE @g_false_value VARCHAR(5);
SET @g_true_value = 'TRUE';
SET @g_false_value = 'FALSE';

IF @p_datatype = 'STRING'
	SET @v_output_value = CAST(REPLACE(@p_value,',','.') AS NUMERIC);
ELSE IF @p_datatype = 'NUMBER' 
	SET @v_output_value =  dbo.vtl_standardize_number(@p_value); 
ELSE IF @p_datatype = 'BOOLEAN'
	BEGIN
    	IF UPPER(@p_value) IN ( @g_true_value, '1') 
        	SET @v_output_value = 1; 
        else 
       		SET @v_output_value = 0; 
	END
  
return @v_output_value;

END

------------------------------------------------------------------------

CREATE FUNCTION dbo.vtl_cast_to_number(@p_value VARCHAR(50), @p_datatype VARCHAR(50), @p_mask VARCHAR(50))
 RETURNS FLOAT
AS 
BEGIN
	
DECLARE @v_output_value FLOAT;
DECLARE @g_true_value VARCHAR(4);
DECLARE @g_false_value VARCHAR(5);
SET @g_true_value = 'TRUE';
SET @g_false_value = 'FALSE';

IF @p_datatype = 'BOOLEAN' 
	BEGIN
    IF UPPER(@p_value) IN (@g_true_value, '1') 
    	SET @v_output_value = 1; 
    ELSE
    	SET @v_output_value = 0; 
    END
ELSE IF @p_datatype = 'STRING'
	BEGIN
	IF CAST(REPLACE(@p_value,',','.') AS FLOAT) > CAST(REPLACE(REPLACE(@p_mask,'D','9'),',','.') AS FLOAT)
		SET @v_output_value = @p_mask;
	ELSE
		SET @v_output_value = CAST(REPLACE(@p_value,',','.') AS FLOAT);
	END
ELSE IF @p_datatype = 'INTEGER'
	SET @v_output_value = dbo.vtl_standardize_number(@p_value);
  
return @v_output_value;

END

------------------------------------------------------------------------

CREATE FUNCTION dbo.vtl_cast_to_boolean(@p_value VARCHAR(50), @p_datatype VARCHAR(50))
 RETURNS bit
AS 
BEGIN
	
DECLARE @v_output_value bit;
DECLARE @g_true_value VARCHAR(4);
DECLARE @g_false_value VARCHAR(5);
SET @g_true_value = 'TRUE';
SET @g_false_value = 'FALSE';

IF @p_datatype = 'INTEGER'  
	BEGIN
	IF @p_value <> '0'  
    	SET @v_output_value = 1;
	ELSE 
		SET @v_output_value = 0;
	END
ELSE IF @p_datatype = 'NUMBER'  
	BEGIN 
	IF @p_value <> '0'  
		SET @v_output_value = 1;
	else 
		SET @v_output_value = 0;
	END
/*ELSE IF @p_datatype = 'STRING'  
	BEGIN 
    	IF @p_value = @g_true_value  
        	SET @v_output_value = 1;
    	ELSE IF @p_value = @g_false_value  
        	SET @v_output_value = 0;
	END*/       

return @v_output_value;

END

------------------------------------------------------------------------

CREATE FUNCTION dbo.vtl_cast_to_timeperiod(@p_value VARCHAR(50), @p_datatype VARCHAR(50), @p_mask VARCHAR(50))
 RETURNS VARCHAR(50)
AS 
BEGIN
	
DECLARE @v_retval VARCHAR(50);
DECLARE @v_output_value VARCHAR(50);
DECLARE @g_period_day VARCHAR(1);
SET @g_period_day = 'D';

IF @p_datatype = 'DATE' 
	SET @v_output_value = CONCAT(CONVERT(varchar, datepart(year, CONVERT(DATE,@p_value, CONVERT(int, @p_mask)))) , @g_period_day , CONVERT(varchar, datepart(dy, CONVERT(DATE,@p_value, CONVERT(int, @p_mask)))));
	--SET @v_output_value = CONVERT(varchar, @p_value, CONVERT(int, @p_mask)) --versione senza datepart
ELSE IF @p_datatype = 'STRING'
	BEGIN
	SET @v_retval = dbo.vtl_period_indicator(@p_value);
	IF @v_retval = 'Invalid format'
		SET @v_output_value = 'Not castable value';
	ELSE
		SET @v_output_value = @p_value;
	END
	
return @v_output_value;

END

------------------------------------------------------------------------

CREATE FUNCTION dbo.vtl_cast_to_string(@p_value VARCHAR(50), @p_datatype VARCHAR(50), @p_mask VARCHAR(50))
 RETURNS VARCHAR(50)
AS 
BEGIN
	
DECLARE @v_output_value VARCHAR(50);
DECLARE @g_true_value VARCHAR(4);
DECLARE @g_false_value VARCHAR(5);
SET @g_true_value = 'TRUE';
SET @g_false_value = 'FALSE';

IF @p_datatype = 'INTEGER' OR @p_datatype = 'NUMBER'  
	SET @v_output_value =  @p_value;
ELSE IF @p_datatype = 'BOOLEAN'  
	BEGIN       
    IF UPPER(@p_value) IN (@g_true_value, '1') 
    	SET @v_output_value = '1'; 
    else 
    	SET @v_output_value = '0'; 
    END
ELSE IF @p_datatype = 'TIME'
	BEGIN
		IF 'YYYY-MM-DD/YYYY-MM-DD' = @p_mask COLLATE SQL_Latin1_General_CP1_CS_AS
			BEGIN
				SET @v_left_mask = '23';
				SET @v_right_mask = '23';
				SET @v_position = charindex(@g_time_separator, @p_value);
				SET @v_left_value = TRIM(SUBSTRING(@p_value, 1, @v_position - 1));
				SET @v_righ_value = TRIM(SUBSTRING(@p_value, @v_position + 1, LEN(@p_value)));
				SET @v_output_value = CONCAT(CONVERT(DATE, @v_left_value, CONVERT(int,@v_left_mask)) , @g_time_separator , CONVERT(DATE, @v_righ_value, CONVERT(int,@v_right_mask)));
			END
		ELSE
			--Aggiunto per generare un errore nel caso la maschera sia diversa (altrimenti ritrnava NULL)
			RETURN CAST(@p_mask AS DATE);
	END
ELSE IF @p_datatype =  'DATE' 
	IF  'YYYY-MM-DD' = @p_mask COLLATE SQL_Latin1_General_CP1_CS_AS
		SET @v_output_value = CONVERT(varchar,CONVERT(date, @p_value), CONVERT(int, '23'));
	ELSE
		--Aggiunto per generare un errore nel caso la maschera sia diversa (altrimenti ritrnava NULL)
		RETURN CAST(@p_mask AS DATE);
ELSE IF @p_datatype = 'DURATION'  
	SET @v_output_value = @p_value; --to_char(p_value);
ELSE IF @p_datatype = 'TIME_PERIOD'  
	SET @v_output_value = @p_value;
   
return @v_output_value;

END

------------------------------------------------------------------------

CREATE FUNCTION dbo.vtl_cast_to_string_date(@p_value DATE, @p_datatype VARCHAR(50), @p_mask VARCHAR(50))
RETURNS VARCHAR(50)
AS
BEGIN
	DECLARE @v_output_value VARCHAR(50);

	IF @p_datatype = 'DATE'
		BEGIN
		IF 'YYYY-MM-DD' = @p_mask COLLATE SQL_Latin1_General_CP1_CS_AS
			SET @v_output_value = CONVERT(varchar, @p_value, 23);
		ELSE
			--Aggiunto per generare un errore nel caso la maschera sia diversa (altrimenti ritrnava NULL)
			RETURN CAST(@p_mask AS DATE);
		END
	RETURN @v_output_value;

END

------------------------------------------------------------------------

CREATE FUNCTION [dbo].[vtl_cast_to_duration](@p_value VARCHAR(50), @p_datatype VARCHAR(50), @p_mask VARCHAR(50))
 RETURNS VARCHAR(50)
AS 
BEGIN

DECLARE @V_LN_MASK NUMERIC;
DECLARE @V_LN_VALUE NUMERIC;
DECLARE @V_MASK VARCHAR(50);
DECLARE @V_VALUE VARCHAR(50);
DECLARE @V_VALUE_TMP VARCHAR(50);
DECLARE @V_VALUE_N FLOAT;
DECLARE @V_P_MASK VARCHAR(1000);
DECLARE @V_P_VALUE VARCHAR(50);
DECLARE @V_CAST VARCHAR(2); 
DECLARE @V_OUTPUT_VALUE VARCHAR(50);
DECLARE @V_LOOP NUMERIC;
DECLARE @V_SEARCH_CHAR VARCHAR(1);
DECLARE @V_COUNT_PERIOD NUMERIC;

DECLARE @TEST NUMERIC;
DECLARE @TEST_STRING VARCHAR(1);
SET @TEST_STRING = '';

DECLARE @PERIOD_SEPARATOR VARCHAR(2);
SET @PERIOD_SEPARATOR = '\';
SET @V_P_MASK = '';
SET @V_P_VALUE = ''; 

SET @V_CAST = 'OK';
SET @V_LOOP = 1;
SET @V_COUNT_PERIOD = 0;

SET @V_OUTPUT_VALUE = @p_value;
SET @V_VALUE = @p_value;
SET @V_VALUE_TMP = @p_value;
SET @V_MASK = @p_mask;

SET @V_LN_MASK = LEN(REPLACE(@p_mask, @PERIOD_SEPARATOR, ''));

IF (@V_LN_MASK <> LEN(@p_value) OR (@V_VALUE NOT LIKE 'P%') OR (@V_MASK NOT LIKE '!\P%' ESCAPE '!'))
	SET @V_CAST = 'KO';
ELSE
	BEGIN
		SET @V_LN_MASK = LEN(@V_MASK);

		WHILE (@V_LOOP < @V_LN_MASK)
		BEGIN
			SET @V_P_MASK = CONCAT(@V_P_MASK, SUBSTRING(@V_MASK, CHARINDEX(@PERIOD_SEPARATOR, @V_MASK, @V_LOOP) +1, 1));

			IF (@V_P_MASK IS NOT NULL)
				SET @V_LOOP = CHARINDEX(@PERIOD_SEPARATOR, @V_MASK, @V_LOOP) +2;
			ELSE
				SET @V_LOOP = @V_LN_MASK +1; --EXIT LOOP
		END;

		SET @V_LN_VALUE = LEN(@V_VALUE);
		SET @V_COUNT_PERIOD = LEN(@V_P_MASK);
		SET @V_LOOP = 1;

		WHILE (@V_LOOP <= @V_COUNT_PERIOD)
		BEGIN
			SET @V_SEARCH_CHAR = SUBSTRING(@V_P_MASK, @V_LOOP, 1);
			SET @V_P_VALUE = CONCAT(@V_P_VALUE, SUBSTRING(@V_VALUE_TMP, CHARINDEX(@V_SEARCH_CHAR, @V_VALUE_TMP) , 1));

			IF((@V_P_VALUE IS NOT NULL) AND (CHARINDEX(@V_SEARCH_CHAR, @V_VALUE_TMP) > 0))
			BEGIN
				SET @V_VALUE_TMP = SUBSTRING(@V_VALUE_TMP, CHARINDEX(@V_SEARCH_CHAR, @V_VALUE_TMP), LEN(@V_VALUE_TMP));
				SET  @V_VALUE = REPLACE(@V_VALUE, @V_SEARCH_CHAR, '');
			END

			SET @V_LOOP = @V_LOOP +1;
		END;
		SET @V_VALUE = REPLACE(@V_VALUE, 'T', '');

		IF(@V_P_MASK = @V_P_VALUE)
			SET @V_VALUE_N = CAST(@V_VALUE AS FLOAT); 
		ELSE
			SET @V_CAST = 'KO';
	END

	IF (@V_CAST = 'KO')
	BEGIN
		SET @TEST = CAST('Invalid cast.' AS NUMERIC); 
	END

return @V_OUTPUT_VALUE;

END

------------------------------------------------------------------------

CREATE FUNCTION dbo.vtl_cast_to_date(@p_value VARCHAR(50), @p_datatype VARCHAR(50),@p_mask VARCHAR(50))
 RETURNS DATE
AS 
BEGIN
DECLARE @v_start VARCHAR(10);
DECLARE @v_period VARCHAR(1);
DECLARE @v_year VARCHAR(4);
DECLARE @v_first_char INTEGER;
DECLARE @v_output_value DATE;
DECLARE @g_true_value VARCHAR(4);
DECLARE @g_false_value VARCHAR(5);
DECLARE @g_time_separator VARCHAR(1);
DECLARE @g_period_day VARCHAR(1);
DECLARE @g_period_week VARCHAR(1);
DECLARE @g_period_month VARCHAR(1);
DECLARE @g_period_quarter VARCHAR(1);
DECLARE @g_period_semester VARCHAR(1);
DECLARE @g_period_year VARCHAR(1);
SET @g_true_value = 'TRUE';
SET @g_false_value = 'FALSE';
SET @g_time_separator = '/';
SET @g_period_day = 'D';
SET @g_period_week = 'W';
SET @g_period_month = 'M';
SET @g_period_quarter = 'Q';
SET @g_period_semester = 'S';
SET @g_period_year = 'A';



            IF @p_datatype = 'STRING' 
				BEGIN
				IF (LEN(@p_value) <> LEN(@p_mask))
					SET @v_first_char = CAST('Invalid cast.' AS NUMERIC);

				IF 'YYYY-MM-DD' = @p_mask COLLATE SQL_Latin1_General_CP1_CS_AS 
           			SET @v_output_value = Convert(varchar,@p_value,convert(int, '120'));
				ELSE
					--Aggiunto per generare un errore nel caso la maschera sia diversa (altrimenti ritrnava NULL)
					RETURN CAST(@p_mask AS DATE);
				END 
            ELSE IF @p_datatype = 'TIME_PERIOD'
            	BEGIN
                SET @v_start = COALESCE(@p_mask, 'START');
                
                SET @v_year = SUBSTRING(@p_value, 1, 4);
                
                SET @v_first_char = 5;
                SET @v_period = SUBSTRING(@p_value, @v_first_char, 1);

                IF @v_period = '-' 
                	BEGIN
                    SET @v_first_char = 6;
                    SET @v_period = SUBSTRING(@p_value, @v_first_char, 1);
                	END
                
                SET @v_first_char = @v_first_char +1;
                
               
                    IF @v_period = @g_period_day
                        SET @v_output_value = CONVERT(varchar, dateadd(d, CONVERT(int,SUBSTRING(@p_value, @v_first_char, 3)-1), CONVERT(DATE, @v_year + '0101')));
                       				
                    ELSE IF @v_period = @g_period_week 
                    	BEGIN
                        IF @v_start = 'START'
                        	SET @v_output_value = dbo.vtl_iso_week_to_date(@v_year, SUBSTRING(@p_value, @v_first_char, 2) );--trunc(to_date('2010' + to_char(SUBSTRING(@p_value, @v_first_char, 2) * 7, '000'), 'YYYYDDD'), 'IW');
                        ELSE
                        	SET @v_output_value = dateadd(d, 6, dbo.vtl_iso_week_to_date(@v_year, SUBSTRING(@p_value, @v_first_char, 2) ));
                            --SET @v_output_value = trunc(to_date('2010' + to_char(SUBSTRING(@p_value, @v_first_char, 2) * 7, '000'), 'YYYYDDD'), 'IW') +6;
                        END
                    ELSE IF @v_period = @g_period_month 
                    	BEGIN
                        IF @v_start = 'START' 
                            SET @v_output_value = CONVERT(varchar, @v_year + SUBSTRING(@p_value, @v_first_char, 2) + '01', 112);
                        ELSE
                        	SET @v_output_value = EOMONTH(CONVERT(varchar, @v_year + SUBSTRING(@p_value, @v_first_char, 2) + '01', 112));
                        END
                    ELSE IF @v_period = @g_period_quarter 
                    	BEGIN
                        IF SUBSTRING(@p_value, @v_first_char, 1) = '1' 
                        	BEGIN
                            IF @v_start = 'START' 
                                SET @v_output_value = CONVERT(varchar, @v_year + '0101', 112);
                            ELSE
                                SET @v_output_value = CONVERT(varchar, @v_year + '0331', 112);
                            END
                        ELSE IF SUBSTRING(@p_value, @v_first_char, 1) = '2' 
                            BEGIN
                        	IF @v_start = 'START' 
                                SET @v_output_value = CONVERT(varchar, @v_year + '0401', 112);
                            ELSE
                                SET @v_output_value = CONVERT(varchar, @v_year + '0630', 112);
  							END
                        ELSE IF SUBSTRING(@p_value, @v_first_char, 1) = '3' 
                 			BEGIN
                        	IF @v_start = 'START' 
                                SET @v_output_value = CONVERT(varchar, @v_year + '0701', 112);
                			ELSE
                                SET @v_output_value = CONVERT(varchar, @v_year + '0930', 112);
                            END
                        ELSE
                        	BEGIN
                            IF @v_start = 'START' 
                                SET @v_output_value = CONVERT(varchar, @v_year + '1001', 112);
                            ELSE
                                SET @v_output_value = CONVERT(varchar, @v_year + '1231', 112);
                            END
                        END             
                    ELSE IF @v_period = @g_period_semester 
                    	BEGIN
                        IF SUBSTRING(@p_value, @v_first_char, 1) = '1' 
                            BEGIN
                        	IF @v_start = 'START' 
                                SET @v_output_value = CONVERT(varchar, @v_year + '0101', 112);
                            ELSE
                                SET @v_output_value = CONVERT(varchar, @v_year + '0630', 112);
                            END
                        ELSE
                        	BEGIN
                            IF @v_start = 'START' 
                                SET @v_output_value = CONVERT(varchar, @v_year + '0701', 112);
                            ELSE
                                SET @v_output_value = CONVERT(varchar, @v_year + '1231', 112);
                            END
                        END
                    ELSE IF @v_period = @g_period_year 
                    	BEGIN
                        IF @v_start = 'START' 
                           	SET @v_output_value = CONVERT(varchar, @v_year + '0101', 112);
                        ELSE
                            SET @v_output_value = CONVERT(varchar, @v_year + '1231', 112);
                        END
     			END
        return @v_output_value;

END

------------------------------------------------------------------------

CREATE FUNCTION dbo.vtl_cast_to_time(@p_value VARCHAR(50), @p_datatype VARCHAR(50),@p_mask VARCHAR(50))
 RETURNS VARCHAR(50)
AS 
BEGIN
	
DECLARE @v_period VARCHAR(1);
DECLARE @v_year VARCHAR(4);
DECLARE @v_first_char INTEGER;
DECLARE @v_output_value varchar(50); 
DECLARE @g_true_value VARCHAR(4);
DECLARE @g_false_value VARCHAR(5);
DECLARE @g_time_separator VARCHAR(1);
DECLARE @g_period_day VARCHAR(1);
DECLARE @g_period_week VARCHAR(1);
DECLARE @g_period_month VARCHAR(1);
DECLARE @g_period_quarter VARCHAR(1);
DECLARE @g_period_semester VARCHAR(1);
DECLARE @g_period_year VARCHAR(1);
DECLARE @v_position INTEGER;
DECLARE @v_left_mask VARCHAR(50);
DECLARE @v_right_mask VARCHAR(50);
DECLARE @v_left_value VARCHAR(50);
DECLARE @v_righ_value VARCHAR(50);

SET @g_true_value = 'TRUE';
SET @g_false_value = 'FALSE';
SET @g_time_separator = '/';
SET @g_period_day = 'D';
SET @g_period_week = 'W';
SET @g_period_month = 'M';
SET @g_period_quarter = 'Q';
SET @g_period_semester = 'S';
SET @g_period_year = 'A';
	
	
            IF( @p_datatype = 'STRING' ) 
            	BEGIN
				IF (LEN(@p_value) <> LEN(@p_mask))
					SET @v_position = CAST('Invalid cast.' AS NUMERIC);
                SET @v_position = charindex(@g_time_separator, @p_mask);
                IF (@v_position <> 0 )
                	BEGIN
                    SET @v_left_mask = TRIM(SUBSTRING(@p_mask, 1, @v_position - 1));
					IF ('YYYY-MM-DD' = @v_left_mask COLLATE SQL_Latin1_General_CP1_CS_AS)
						SET @v_left_mask = '102';
					ELSE
						--Aggiunto per generare un errore nel caso la maschera sia diversa (altrimenti ritrnava NULL)
						RETURN CAST(@v_left_mask AS DATE);

                    SET @v_right_mask = TRIM(SUBSTRING(@p_mask, @v_position + 1, LEN(@p_mask)));
					IF ('YYYY-MM-DD' = @v_right_mask COLLATE SQL_Latin1_General_CP1_CS_AS)
						SET @v_right_mask = '102';
					ELSE
						--Aggiunto per generare un errore nel caso la maschera sia diversa (altrimenti ritrnava NULL)
						RETURN CAST(@v_right_mask AS DATE);

                    SET @v_position = charindex( @g_time_separator, @p_value);

                    SET @v_left_value = TRIM(SUBSTRING(@p_value, 1, @v_position - 1));
                    SET @v_righ_value = TRIM(SUBSTRING(@p_value, @v_position + 1, LEN(@p_value)));

                    --SET @v_output_value = CONCAT(DATE_FORMAT(STR_TO_DATE(@v_left_value, @v_left_mask), @v_left_mask) , @g_time_separator , DATE_FORMAT(STR_TO_DATE(@v_righ_value, @v_right_mask), @v_right_mask));
                   	SET @v_output_value = CONCAT(CONVERT(DATE, @v_left_value, CONVERT(int,@v_left_mask)) , @g_time_separator , CONVERT(DATE, @v_righ_value, CONVERT(int,@v_right_mask)));
                	END
                ELSE
					SET @v_position = CAST('Invalid cast.' AS NUMERIC);
                END
             
           	ELSE IF( @p_datatype = 'DATE' ) SET @v_output_value = CONCAT(CONVERT(DATE, @p_value, 102) , '/' , CONVERT(DATE, @p_value, 102));
            ELSE IF( @p_datatype = 'TIME_PERIOD' )
            	BEGIN
                SET @v_year = SUBSTRING(@p_value, 1, 4);
                
                SET @v_first_char = 5;
                SET @v_period = SUBSTRING(@p_value, @v_first_char, 1);

                IF (@v_period = '-' )
                	BEGIN
                    SET @v_first_char = 6;
                    SET @v_period = SUBSTRING(@p_value, @v_first_char, 1);
                   	END
                
                SET @v_first_char = @v_first_char +1;
                
                
                    IF( @v_period = @g_period_day )
                        --SET @v_output_value = CONCAT(DATE_FORMAT(STR_TO_DATE(CONCAT(@v_year , SUBSTRING(@p_value, @v_first_char, 3)), '%Y%j'), '%Y-%m-%d') , '/' , DATE_FORMAT(STR_TO_DATE(CONCAT(@v_year , SUBSTRING(@p_value, @v_first_char, 3)), '%Y%j'), '%Y-%m-%d'));
                    	SET @v_output_value = CONCAT(CONVERT(varchar, dateadd(d, CONVERT(int,SUBSTRING(@p_value, @v_first_char, 3)-1), CONVERT(DATE, @v_year + '0101'))) , '/' , CONVERT(varchar, dateadd(d, CONVERT(int,SUBSTRING(@p_value, @v_first_char, 3)-1), CONVERT(DATE, @v_year + '0101'))));
                    
                    ELSE IF( @v_period = @g_period_week )
                        --SET @v_output_value = CONCAT(DATE_FORMAT(trunc(STR_TO_DATE(CONCAT(@v_year , DATE_FORMAT(SUBSTRING(@p_value, @v_first_char, 2) * 7, '000')), '%Y%j'), 'IW'), '%Y-%m-%d') , '/' , DATE_FORMAT(trunc(STR_TO_DATE(CONCAT(@v_year , DATE_FORMAT(SUBSTRING(@p_value, @v_first_char, 2) * 7, '000')), '%Y%j'), 'IW') +6, '%Y-%m-%d'));
                    	SET @v_output_value = CONCAT(dbo.vtl_iso_week_to_date(@v_year, SUBSTRING(@p_value, @v_first_char, 2) ), '/' ,dateadd(d, 6, dbo.vtl_iso_week_to_date(@v_year, SUBSTRING(@p_value, @v_first_char, 2) )));
                    ELSE IF( @v_period = @g_period_month )
                        SET @v_output_value = CONCAT(CONVERT(date, @v_year + SUBSTRING(@p_value, @v_first_char, 2) + '01', 112) , '/' , EOMONTH(CONVERT(varchar, @v_year + SUBSTRING(@p_value, @v_first_char, 2) + '01', 112)));
                    ELSE IF( @v_period = @g_period_quarter )
                    	BEGIN
                        IF (SUBSTRING(@p_value, @v_first_char, 1) = '1' )
                            SET @v_output_value = CONCAT(CONVERT(DATE, @v_year + '0101', 112) , '/' , CONVERT(DATE, @v_year + '0331', 112));
                        ELSE IF (SUBSTRING(@p_value, @v_first_char, 1) = '2' )
                            SET @v_output_value = CONCAT(CONVERT(DATE, @v_year + '0401', 112) , '/' , CONVERT(DATE, @v_year + '0630', 112));
                        ELSE IF (SUBSTRING(@p_value, @v_first_char, 1) = '3' )
                            SET @v_output_value = CONCAT(CONVERT(DATE, @v_year + '0701', 112) , '/' , CONVERT(DATE, @v_year + '0930', 112));
                        ELSE
                            SET @v_output_value = CONCAT(CONVERT(DATE, @v_year + '1001', 112) , '/' , CONVERT(DATE, @v_year + '1231', 112));
                        END        
                    ELSE IF( @v_period = @g_period_semester )
                    	BEGIN
                        IF (SUBSTRING(@p_value, @v_first_char, 1) = '1' )
                            SET @v_output_value = CONCAT(CONVERT(DATE, @v_year + '0101', 112) , '/' , CONVERT(DATE, @v_year + '0630', 112));
                        ELSE
                            SET @v_output_value = CONCAT(CONVERT(DATE, @v_year + '0701', 112) , '/' , CONVERT(DATE, @v_year + '1231', 112));
                        END
                    ELSE IF( @v_period = @g_period_year )
                            SET @v_output_value = CONCAT(CONVERT(DATE, @v_year + '0101', 112) , '/' , CONVERT(DATE, @v_year + '1231', 112));
                 END        
      
  
        return @v_output_value;
END

------------------------------------------------------------------------

CREATE FUNCTION dbo.vtl_cast_to_time_date(@p_value DATE, @p_datatype VARCHAR(50), @p_mask VARCHAR(50))
RETURNS VARCHAR(50)
AS
BEGIN
	DECLARE @v_output_value VARCHAR(50);

	IF @p_datatype = 'DATE'
		BEGIN
		IF 'YYYY-MM-DD' = @p_mask COLLATE SQL_Latin1_General_CP1_CS_AS
			SET @v_output_value = CONCAT(CONVERT(varchar, @p_value, 23), '/' , CONVERT(varchar, @p_value, 23));
		ELSE
			--Aggiunto per generare un errore nel caso la maschera sia diversa (altrimenti ritrnava NULL)
			RETURN CAST(@p_mask AS DATE);
		END
	RETURN @v_output_value;

END

------------------------------------------------------------------------

CREATE FUNCTION dbo.convert_from_date(@p_timeval DATE,@p_period VARCHAR(50),@date_value VARCHAR(50))
 RETURNS VARCHAR(50)
AS 
BEGIN
	
DECLARE @v_position INT;
DECLARE @v_retval VARCHAR(50);

DECLARE @g_true_value VARCHAR(4);
DECLARE @g_false_value VARCHAR(5);
DECLARE @g_time_separator VARCHAR(1);
DECLARE @g_period_day VARCHAR(1);
DECLARE @g_period_week VARCHAR(1);
DECLARE @g_period_month VARCHAR(1);
DECLARE @g_period_quarter VARCHAR(1);
DECLARE @g_period_semester VARCHAR(1);
DECLARE @g_period_year VARCHAR(1);
DECLARE @g_period_date_time VARCHAR(1);

SET @g_true_value = 'TRUE';
SET @g_false_value = 'FALSE';
SET @g_time_separator = '/';
SET @g_period_day = 'D';
SET @g_period_week = 'W';
SET @g_period_month = 'M';
SET @g_period_quarter = 'Q';
SET @g_period_semester = 'S';
SET @g_period_year = 'A';
SET @g_period_date_time = 'T';

            IF @p_period = @g_period_day
                --%Y%jd format
                SET @v_retval = CONCAT(DATEPART(YEAR, @p_timeval) , @g_period_day , REPLACE(STR(DATEPART(dy ,@p_timeval), 3),' ','0'));
            ELSE IF @p_period = @g_period_week
                --%YWww format
                SET @v_retval = CONCAT(DATEPART(YEAR, @p_timeval) , @g_period_week , REPLACE(STR(DATEPART(WEEK ,@p_timeval), 2),' ','0'));
            ELSE IF @p_period = @g_period_month
            	BEGIN
                SET @v_position = CHARINDEX(@g_time_separator, @date_value);
                
                IF @v_position <> 0
                    --%Y-%m/%Y-%m format
                    SET @v_retval = CONCAT(DATEPART(YEAR, @p_timeval) , '-' , SUBSTRING(@date_value, 6, 2) , @g_time_separator , DATEPART(YEAR, @p_timeval) , '-' , SUBSTRING(@date_value, 14, 2));
                ELSE
                    --%Y%mm format
                    SET @v_retval = CONCAT(DATEPART(YEAR, @p_timeval) , @g_period_month , REPLACE(STR(DATEPART(MONTH ,@p_timeval), 2),' ','0'));
                END
            ELSE IF @p_period = @g_period_quarter
            	BEGIN
                --%YQq format
                IF DATEPART(MONTH ,@p_timeval) <= 3 
                    SET @v_retval = CONCAT(DATEPART(YEAR, @p_timeval) , @g_period_quarter , '1');
                ELSE IF DATEPART(MONTH ,@p_timeval) <= 6 
                    SET @v_retval = CONCAT(DATEPART(YEAR, @p_timeval) , @g_period_quarter , '2');
                ELSE IF DATEPART(MONTH ,@p_timeval) <= 9 
                    SET @v_retval = CONCAT(DATEPART(YEAR, @p_timeval) , @g_period_quarter , '3');
                ELSE
                    SET @v_retval = CONCAT(DATEPART(YEAR, @p_timeval) , @g_period_quarter , '4');
                END
            ELSE IF @p_period = @g_period_semester
            	BEGIN
                --%YSs format
                IF DATEPART(MONTH ,@p_timeval) <= 6 
                    SET @v_retval = CONCAT(DATEPART(YEAR, @p_timeval) , @g_period_semester , '1');
                ELSE
                    SET @v_retval = CONCAT(DATEPART(YEAR, @p_timeval) , @g_period_semester , '2');
                END
            ELSE IF @p_period = @g_period_year
                --%YA format
                SET @v_retval = DATEPART(YEAR, @p_timeval);
            ELSE IF @p_period = @g_period_date_time
                --%Y-%m-DD
                SET @v_retval = CONCAT(DATEPART(YEAR, @p_timeval) , '-' , SUBSTRING(@date_value, 6, 5));

        
		return @v_retval;

END

------------------------------------------------------------------------

CREATE FUNCTION dbo.convert_to_date(@p_timeval VARCHAR(50))
 RETURNS DATE
AS 
BEGIN
	
DECLARE @v_size INT;
DECLARE @v_position INT;
DECLARE @v_period VARCHAR(1);
DECLARE @v_retval DATE;

DECLARE @g_true_value VARCHAR(4);
DECLARE @g_false_value VARCHAR(5);
DECLARE @g_time_separator VARCHAR(1);
DECLARE @g_period_day VARCHAR(1);
DECLARE @g_period_week VARCHAR(1);
DECLARE @g_period_month VARCHAR(1);
DECLARE @g_period_quarter VARCHAR(1);
DECLARE @g_period_semester VARCHAR(1);
DECLARE @g_period_year VARCHAR(1);

SET @g_true_value = 'TRUE';
SET @g_false_value = 'FALSE';
SET @g_time_separator = '/';
SET @g_period_day = 'D';
SET @g_period_week = 'W';
SET @g_period_month = 'M';
SET @g_period_quarter = 'Q';
SET @g_period_semester = 'S';
SET @g_period_year = 'A';


SET @v_size = LEN(TRIM(@p_timeval));

            IF @v_size = 4 
                SET @v_retval = CONVERT(varchar, CONCAT(@p_timeval, '-01-01'), 23);
            ELSE IF @v_size =  10
                SET @v_retval = CONVERT(varchar, @p_timeval, 23);
            ELSE
            	BEGIN
                IF @v_size < 5
                    SET @v_retval = null;
                ELSE
                	BEGIN
                    SET @v_position = CHARINDEX(@g_time_separator, @p_timeval);
                    
                    IF @v_position <> 0
                        SET @v_retval = CONVERT(varchar, CONCAT(substring(@p_timeval, 1, @v_position -1) , '-01'), 23);
                    ELSE
                       SET @v_retval = dbo.vtl_cast_to_date(@p_timeval, 'TIME_PERIOD', 'START');
                    END
                END
        
		return @v_retval;

END

------------------------------------------------------------------------

CREATE PROCEDURE dbo.vtl_fill_time_series(@p_input_dataset VARCHAR(1000), @p_id_list VARCHAR(1000), @p_id_time VARCHAR(1000), @p_limits_method VARCHAR(1000))
AS 
BEGIN
	DECLARE @v_input_dataset VARCHAR(1000);
    DECLARE @v_id_list VARCHAR(1000);
    DECLARE @v_id_time VARCHAR(1000);
    DECLARE @v_limits_method VARCHAR(1000);
	
    
    DECLARE @v_if_exists_cycle_tab int;
    DECLARE @v_if_exists_dsr_tab int;

    DECLARE @v_min_all_time DATE;
    DECLARE @v_max_all_time DATE;
    DECLARE @v_min_time VARCHAR(30);
    DECLARE @v_max_time VARCHAR(30);
    DECLARE @v_period VARCHAR(10);
    DECLARE @v_loop_time_1 VARCHAR(100);
    DECLARE @v_loop_time_2 VARCHAR(100);
    DECLARE @v_loop_row int; 
    
    DECLARE @v_loop_row_end int;

    DECLARE @v_cycle_tab_name VARCHAR(50); 
    DECLARE @v_dsr_tab_name VARCHAR(50);
	
    DECLARE @v_sql_stmt_single NVARCHAR(2000);
    DECLARE @v_sql_stmt_all NVARCHAR(2000);
	DECLARE @v_stmt_loop_row_end VARCHAR(2000);
	DECLARE @v_stmt_create_dsr NVARCHAR(2000); 
  	
  	DECLARE @sqlCommand NVARCHAR(1000);
	DECLARE @ValueTable TABLE ( Value DATE );
 	DECLARE @ValueTable2 TABLE ( Value DATE );
  
   	SET @v_input_dataset = @p_input_dataset;
    SET @v_id_list = @p_id_list;
    SET @v_id_time = @p_id_time;
    SET @v_limits_method = @p_limits_method;

    SET @v_loop_row = 1;
    SET @v_cycle_tab_name = 'TEMPORARY_FTS_CYCLE';
    SET @v_dsr_tab_name = 'TEMPORARY_FTS';
 
	SET @v_sql_stmt_single =
	'SELECT t1.* INTO '+@v_cycle_tab_name+' FROM ( 
	SELECT DISTINCT '
                 +@v_id_list+', '+
                 'dbo.VTL_PERIOD_INDICATOR('+@v_id_time+') AS PERIOD, 
                 MAX('+@v_id_time+') OVER (PARTITION BY '+@v_id_list+', dbo.VTL_PERIOD_INDICATOR('+@v_id_time+')) AS MAX_TIME, 
                 MIN('+@v_id_time+') OVER (PARTITION BY '+@v_id_list+',  dbo.VTL_PERIOD_INDICATOR('+@v_id_time+')) AS MIN_TIME,
       DENSE_RANK() OVER (ORDER BY '+@v_id_list+', dbo.VTL_PERIOD_INDICATOR('+@v_id_time+')) AS N_ROW
	FROM '+@v_input_dataset+
    ' ORDER BY ' +@v_id_list+
                      ' , dbo.VTL_PERIOD_INDICATOR('+@v_id_time+') OFFSET 0 ROWS ) t1';
 
    SET @v_sql_stmt_all =
    'SELECT t1.* INTO '+@v_cycle_tab_name+' FROM ( 
    SELECT DISTINCT '
                 +@v_id_list+', '+
                 'dbo.VTL_PERIOD_INDICATOR('+@v_id_time+') AS PERIOD, 
                 MIN('+@v_id_time+') OVER (PARTITION BY '+@v_id_list+',  dbo.VTL_PERIOD_INDICATOR('+@v_id_time+')) AS MIN_TIME,
                 DENSE_RANK() OVER (ORDER BY '+@v_id_list+', dbo.VTL_PERIOD_INDICATOR('+@v_id_time+')) AS N_ROW
    FROM '+@v_input_dataset+
    ' ORDER BY ' +@v_id_list+
                      ' , dbo.VTL_PERIOD_INDICATOR('+@v_id_time+')  OFFSET 0 ROWS ) t1';

	SET @v_stmt_loop_row_end = 'SELECT COUNT(*) FROM '+@v_cycle_tab_name;
    
    SET @v_stmt_create_dsr = 
    'SELECT t2.* INTO  '+@v_dsr_tab_name+' FROM (  
     SELECT TOP 0 '
                 +@v_id_list+', ' 
                 +@v_id_time+' 
     FROM '+@v_input_dataset+' 
      ) t2';
    
	SELECT @v_if_exists_cycle_tab = COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = @v_cycle_tab_name;

	SELECT @v_if_exists_dsr_tab = COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = @v_dsr_tab_name;

	IF @v_if_exists_cycle_tab=1
		DROP TABLE TEMPORARY_FTS_CYCLE;
        
	IF @v_if_exists_dsr_tab=1
        DROP TABLE TEMPORARY_FTS;

	EXECUTE sp_executesql @v_stmt_create_dsr;
	
	IF @v_limits_method = 'SINGLE'
		BEGIN
		EXECUTE sp_executesql @v_sql_stmt_single;
            
        SELECT @v_loop_row_end = COUNT(*) FROM TEMPORARY_FTS_CYCLE;
        
		WHILE @v_loop_row <= @v_loop_row_end
        	BEGIN
            	SELECT @v_min_time = MIN_TIME FROM TEMPORARY_FTS_CYCLE WHERE N_ROW = @v_loop_row;
                
            	SELECT @v_max_time = MAX_TIME FROM TEMPORARY_FTS_CYCLE WHERE N_ROW = @v_loop_row;
                
				SET @v_loop_time_1 = dbo.VTL_TIMESHIFT(@v_min_time, -1); 
				SET @v_loop_time_2 = dbo.VTL_TIMESHIFT(@v_min_time, -1);
    
				WHILE @v_loop_time_2 <> @v_max_time 
				BEGIN
                	SET @v_loop_time_2 = dbo.VTL_TIMESHIFT(@v_loop_time_1, 1);
        	
                	SET @sqlCommand = CONCAT('INSERT INTO ',@v_dsr_tab_name,' 
                                                          SELECT ',@v_id_list,', ','''',@v_loop_time_2,'''','  
                                                          FROM ',@v_cycle_tab_name,' 
                                                          WHERE N_ROW =', @v_loop_row);
					EXEC sp_executesql @sqlCommand;
                   	
					--COMMIT;
        
                    SET @v_loop_time_1 = @v_loop_time_2;
				END
    
				SET @v_loop_row = @v_loop_row + 1;                
			END
		END
        ELSE
          	BEGIN
	        
			SET @sqlCommand = 'SELECT MIN(dbo.convert_to_date('+@v_id_time+')) FROM '+@v_input_dataset;
			INSERT INTO @ValueTable EXEC sp_executesql @sqlCommand;
			SELECT TOP 1 @v_min_all_time = Value FROM @ValueTable;
               
	       	SET @sqlCommand = 'SELECT MAX(dbo.convert_to_date('+@v_id_time+')) FROM '+@v_input_dataset;
	      	INSERT INTO @ValueTable2 EXEC sp_executesql @sqlCommand;
            SELECT TOP 1 @v_max_all_time = Value FROM @ValueTable2;
	       	
	        EXECUTE sp_executesql @v_sql_stmt_all;
            
            SELECT @v_loop_row_end = COUNT(*) FROM TEMPORARY_FTS_CYCLE;
	       	
            WHILE @v_loop_row <= @v_loop_row_end
            BEGIN
	        	SELECT @v_min_time = MIN_TIME FROM TEMPORARY_FTS_CYCLE WHERE N_ROW = @v_loop_row;
	           	SELECT @v_period = PERIOD FROM TEMPORARY_FTS_CYCLE WHERE N_ROW = @v_loop_row;

              	SET @v_min_time = dbo.convert_from_date(@v_min_all_time, @v_period, @v_min_time);
              	SET @v_max_time = dbo.convert_from_date(@v_max_all_time, @v_period, @v_min_time);                   
               
                SET @v_loop_time_1 = dbo.VTL_TIMESHIFT(@v_min_time, -1); 
                SET @v_loop_time_2 = dbo.VTL_TIMESHIFT(@v_min_time, -1);
    
                WHILE (@v_loop_time_2 <> @v_max_time and @v_loop_time_2 < @v_max_time)
                BEGIN
                	SET @v_loop_time_2 = dbo.VTL_TIMESHIFT(@v_loop_time_1, 1);
        			
                   	SET @sqlCommand = CONCAT('INSERT INTO ',@v_dsr_tab_name,' 
                                                          SELECT ',@v_id_list,', ','''',@v_loop_time_2,'''','  
                                                          FROM ',@v_cycle_tab_name,' 
                                                          WHERE N_ROW =', @v_loop_row);
					EXEC sp_executesql @sqlCommand;
                    
					--COMMIT;
        
                    SET @v_loop_time_1 = @v_loop_time_2;
                END
    
                SET @v_loop_row = @v_loop_row + 1;                
            END            
         END
END

------------------------------------------------------------------------