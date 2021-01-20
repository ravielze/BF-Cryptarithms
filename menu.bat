@echo off

cd %CD%/src/

echo Compiling Program...
javac -d ../bin ./cryptarithms/*.java

echo Running program...
echo --------------------------------
cd ..
cd %CD%/bin
java cryptarithms.CryptaBF
echo --------------------------------
cd ..
