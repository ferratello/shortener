# Simple URL Shortener

Simple application to shorten URLs

##Installation.
Build the application 
  ```bash
  mvn clean package
  ```
Execute the application
  ```bash
  ./shortener-rest/target/shortener-rest-1.0-SNAPSHOT.jar
  ```
Open your browser at: [http://localhost:8081/shortener/index.html](http://localhost:8081/shortener/index.html)

Enjoy!

###Notes from the code side
The application use an **embedded mysql server** that starts on the **13306** port on your **localhost** (preconfigured values)
Page index.html contains an interface to call the REST services:
* **/generate** to obtain a shortened url from the original one
* **/get** to retrieve the original url given a short one

The **REST** service is implemented using _spring-mvc_

**Dependency injection** is implemented using _spring_

**Self-contained executable jar** is implemented using _spring-boot_

#####Use Cases:

* Given a url and a SEO keyword retrieve a short url with the keyword as path <br/>
  eg: **url**: http://www.my.url.com/my_long_path **seo_keyword**: MY_KEY <br/>
  **result**: http://short.com/MY_KEY 
* Given a url only retrieve a short url with a random 4 alphanumeric chars path <br/>
  eg: **url**: http://www.my.url.com/my_long_path_for_random <br/>
  **result**: http://short.com/a3Cg
* Given an existent url retrieve the same short url
* Given a short url retrieve the relative original url url <br/>
  eg: **short url**: http://short.com/a3Cg <br/>
  **result**: http://www.my.url.com/my_long_path_for_random <br/>
  or  **short url**: http://short.com/MY_KEY <br/>
  **result**: http://www.my.url.com/my_long_path
