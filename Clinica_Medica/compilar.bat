@echo off
chcp 65001 > nul
if not exist out mkdir out
dir /s /b src\*.java > fontes.txt
javac -encoding UTF-8 -d out @fontes.txt
if %errorlevel%==0 echo Compilado com sucesso!
del fontes.txt
pause
