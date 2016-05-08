Feature: Scraping product information from Sainsburys web page and presenting it as JSON

	As the user of the urlScrape utility I want to specify a URL for a Sainsburys
	product web page and have the product title, unit price, size of web data, product
	description and total price presented in JSON format.

@wip
Scenario: I have a valid Sainsbury page URL and connectivity is OK

	Given the URL "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html"
	And good connectivity to it
	When the urlScrape utility is run against that URL
	Then the utility returns an accurate JSON representation of the product data
	

