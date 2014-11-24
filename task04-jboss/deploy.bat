set jb=d:\dev\jboss-eap-6.3
set app=banking
set pr=d:\pra\task04
set NOPAUSE=1
start /D "%jb%\bin" jboss-cli.bat --connect shutdown

cd "%pr%"
call gradlew clean ear

del "%jb%\standalone\deployments\%app%.*" /S /Q
copy "%pr%\ear\build\libs\%app%.ear" "%jb%\standalone\deployments\"

rem start /D "%jb%\bin" standalone.bat
start /D "%jb%\bin" standalone.bat --debug 5777
