package fr.iutinfo;

import static spark.Spark.*;

public class HelloWorld {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
        get("/jsonp", (req, res) -> 
"<html><head><script src=\"https://code.jquery.com/jquery-2.1.3.min.js\"/>"+
"<script>" +
"// Using YQL and JSONP "+
"$.ajax({ "+
"url: \"http://query.yahooapis.com/v1/public/yql\", " +
"// The name of the callback parameter, as specified by the YQL service "+
"jsonp: \"callback\", "+
"// Tell jQuery we're expecting JSONP "+
"dataType: \"jsonp\", "+
"// Tell YQL what we want and that we want JSON "+
"data: { "+
"  q: \"select title,abstract,url from search.news where query=\"cat\"\", "+
"  format: \"json\" "+
"}, "+
"// Work with the response "+
"success: function( response ) { "+
"  console.log( response ); // server response "+
"}"+
"}"+
"</script></head><body><h1>Test JSONP</h1>"+
"<script>function callback(data) { alert(data); } </script></body></html>" );
    }
}

