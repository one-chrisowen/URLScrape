# URLScrape
Java application for scraping a Sainsbury's web page to pull out product data and represent it in JSON format


Overview
--------

The purpose of this application is to scrape a Sainsburys web page for a particular set of product data and to present it in JSON format.

The approach taken has been inclusive in terms of the technologies used, in order to demonstrate how they might be combined. The main application uses Spring Boot. The testing is split between a high-level Cucumber feature and a set of JUnit tests. Coverage is not complete, but does reflect where the main development effort has gone in. The unit tests make moderate use of Mockito framework also. JSON-related tools have been avoided on account of their tendency to randomise the output (though that is rather normal for JSON, so maybe GSon or similar should have been used).

Combining the various technologies (particularly the use of Spring) is maybe a bit "over the top" for a small utility such as this, but getting them all working together has proved a rewarding challenge.


Instructions
------------

1) Ensure you have a version of Maven on your machine<B>
2) Download the zipfile of this project<B>
3) Unzip it to a folder of your choice on your machine<B>
4) Open a comand line in yourfolder\URLScrape-master<B>
5) Run 'mvn package' to build a fat executable jar file<B>
   (this will be created in the 'target subdirectory)<B>
6) From yourfolder\URLScrape-master, run:<B>
       'java -jar target\UrlScrape-0.0.1-SNAPSHOT.jar'<B>

       
The 'mvn package' step also runs the various tests.
