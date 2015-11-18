# CountWords
An example on using apache commons CLI

STEP BY STEP HOW TO RUN THIS APP

1. Change to directory containing this file.

2. compile this app:

    mvn package

3. run this app using one of these styles:

    java -cp "target/dependency/*:target/countwords-1.0-SNAPSHOT.jar" com.aldianfazrihady.countwords.CountWords -f YOUR_TEXT_FILE_CONTAINING_WORDS.txt

    java -cp "target/dependency/*:target/countwords-1.0-SNAPSHOT.jar" com.aldianfazrihady.countwords.CountWords < YOUR_TEXT_FILE_CONTAINING_WORDS.txt

    cat YOUR_TEXT_FILE_CONTAINING_WORDS.txt | java -cp "target/dependency/*:target/countwords-1.0-SNAPSHOT.jar" com.aldianfazrihady.countwords.CountWords
