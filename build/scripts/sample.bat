@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  sample startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

@rem Add default JVM options here. You can also use JAVA_OPTS and SAMPLE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windowz variants

if not "%OS%" == "Windows_NT" goto win9xME_args
if "%@eval[2+2]" == "4" goto 4NT_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*
goto execute

:4NT_args
@rem Get arguments from the 4NT Shell from JP Software
set CMD_LINE_ARGS=%$

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\conf\;%APP_HOME%\lib\sample-1.0.0.jar;%APP_HOME%\lib\sample-services-1.0.0.jar;%APP_HOME%\lib\sample-api-1.0.0.jar;%APP_HOME%\lib\spring-core-4.0.1.RELEASE.jar;%APP_HOME%\lib\spring-beans-4.0.1.RELEASE.jar;%APP_HOME%\lib\spring-context-4.0.1.RELEASE.jar;%APP_HOME%\lib\spring-expression-4.0.1.RELEASE.jar;%APP_HOME%\lib\spring-jdbc-4.0.1.RELEASE.jar;%APP_HOME%\lib\spring-orm-4.0.1.RELEASE.jar;%APP_HOME%\lib\spring-oxm-4.0.1.RELEASE.jar;%APP_HOME%\lib\spring-jms-4.0.1.RELEASE.jar;%APP_HOME%\lib\spring-tx-4.0.1.RELEASE.jar;%APP_HOME%\lib\spring-web-4.0.1.RELEASE.jar;%APP_HOME%\lib\spring-webmvc-4.0.1.RELEASE.jar;%APP_HOME%\lib\spring-websocket-4.0.1.RELEASE.jar;%APP_HOME%\lib\curator-x-discovery-1.3.3.jar;%APP_HOME%\lib\slf4j-api-1.7.6.jar;%APP_HOME%\lib\jul-to-slf4j-1.7.6.jar;%APP_HOME%\lib\jcl-over-slf4j-1.7.6.jar;%APP_HOME%\lib\log4j-over-slf4j-1.7.6.jar;%APP_HOME%\lib\logback-classic-1.1.1.jar;%APP_HOME%\lib\commons-collections-3.2.jar;%APP_HOME%\lib\swift-service-0.11.0.jar;%APP_HOME%\lib\spring-aop-4.0.1.RELEASE.jar;%APP_HOME%\lib\curator-recipes-1.3.3.jar;%APP_HOME%\lib\jackson-mapper-asl-1.9.2.jar;%APP_HOME%\lib\zookeeper-3.4.5.jar;%APP_HOME%\lib\logback-core-1.1.1.jar;%APP_HOME%\lib\swift-annotations-0.11.0.jar;%APP_HOME%\lib\swift-codec-0.11.0.jar;%APP_HOME%\lib\libthrift-0.9.1.jar;%APP_HOME%\lib\nifty-client-0.11.0.jar;%APP_HOME%\lib\nifty-core-0.11.0.jar;%APP_HOME%\lib\configuration-0.83.jar;%APP_HOME%\lib\stats-0.83.jar;%APP_HOME%\lib\units-0.83.jar;%APP_HOME%\lib\netty-3.7.0.Final.jar;%APP_HOME%\lib\annotations-2.0.2.jar;%APP_HOME%\lib\jmxutils-1.14.jar;%APP_HOME%\lib\aopalliance-1.0.jar;%APP_HOME%\lib\curator-framework-1.3.3.jar;%APP_HOME%\lib\jackson-core-asl-1.9.2.jar;%APP_HOME%\lib\jline-0.9.94.jar;%APP_HOME%\lib\netty-3.2.2.Final.jar;%APP_HOME%\lib\asm-all-4.1.jar;%APP_HOME%\lib\paranamer-2.5.2.jar;%APP_HOME%\lib\commons-lang3-3.1.jar;%APP_HOME%\lib\httpclient-4.2.5.jar;%APP_HOME%\lib\httpcore-4.2.4.jar;%APP_HOME%\lib\guice-3.0.jar;%APP_HOME%\lib\javax.inject-1.jar;%APP_HOME%\lib\guice-multibindings-3.0.jar;%APP_HOME%\lib\validation-api-1.1.0.Final.jar;%APP_HOME%\lib\bval-jsr303-0.5.jar;%APP_HOME%\lib\cglib-nodep-2.2.2.jar;%APP_HOME%\lib\jackson-annotations-2.1.4.jar;%APP_HOME%\lib\curator-client-1.3.3.jar;%APP_HOME%\lib\commons-codec-1.6.jar;%APP_HOME%\lib\cglib-2.2.1-v20090111.jar;%APP_HOME%\lib\bval-core-0.5.jar;%APP_HOME%\lib\commons-beanutils-core-1.8.3.jar;%APP_HOME%\lib\guava-15.0.jar

@rem Execute sample
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %SAMPLE_OPTS%  -classpath "%CLASSPATH%" cn.thinkjoy.sample.BootStartup %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable SAMPLE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%SAMPLE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
