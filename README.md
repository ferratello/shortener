# Simple URL Shortener

Simple application to shorten URLs

####Start to read the code from Use Cases.

Use Cases:

* Given a url and a SEO keyword retrieve a short url with the keyword as path <br/>
  eg: **url**: http://www.my.url.com/my_long_path **seo_keyword**: MY_KEY <br/>
  **result**: http://short.com/MY_KEY 
* Given a url only retrieve a short url with a random 4 alphanumeric chars path <br/>
  eg: **url**: http://www.my.url.com/my_long_path_for_random <br/>
  **result**: http://short.com/a3Cg
* Given an existent url retrieve the same short url
* Given a short url retrieve the relative original url <br/>
  eg: **short url**: http://short.com/a3Cg <br/>
  **result**: http://www.my.url.com/my_long_path_for_random <br/>
  or  **short url**: http://short.com/MY_KEY <br/>
  **result**: http://www.my.url.com/my_long_path


Used an hexagonal architecture design to prepare the mysql storage implementation
