# Simple URL Shortener

Simple application to shorten URLs

Use Cases:

* Given a url and a SEO keyword retrieve a short url with the keyword as path
  eg: *url*: http://www.my.url.com/my_long_path *seo_keyword*: MY_KEY
  *result*: http://short.com/MY_KEY
* Given a url only retrieve a short url with a random 4 alphanumeric chars path
  eg: *url*: http://www.my.url.com/my_long_path_for_random
  *result*: http://short.com/a3Cg
* Given an existent url retrieve the same short url
* Given a short url retrieve the relative original url
  eg: *short url*: http://short.com/a3Cg
  *result*: http://www.my.url.com/my_long_path_for_random
  or  *short url*: http://short.com/MY_KEY
  *result*: http://www.my.url.com/my_long_path


Use an hexagonal architecture design to prepare the mysql storage implementation
