package edu.escuelaing.arem.ASE.app;

import static spark.Spark.*;

public class SparkWebServer {

    public static void main(String[] args) {
        port(getPort());

        // Rutas estáticas
        staticFileLocation("/public");

        // Rutas dinámicas
        get("/coseno", (req, res) -> cosHtml());
        get("/seno", (req, res) -> sinHtml());
        get("/magnitud", (req, res) -> magHtml());
        get("/palindromo", (req, res) -> palindromeHtml());

        get("/result", (req, res) -> {
            try {
                String op = req.queryParams("op"), resp = "", chain = req.queryParams("chain");
                Double param1 = req.queryParams("num1") != null ? Double.parseDouble(req.queryParams("num1")) : null;
                Double param2 = req.queryParams("num2") != null ? Double.parseDouble(req.queryParams("num2")) : null;

                switch (op) {
                    case "sin":
                        resp += Math.sin(param1);
                        break;
                    case "cos":
                        resp += Math.cos(param1);
                        break;
                    case "mag":
                        resp += Math.sqrt(param1 * param1 + param2 * param2);
                        break;
                    case "palindromo":
                        resp += chain.equals(new StringBuilder(chain).reverse().toString());
                        break;
                    default:
                        resp = "";
                        break;
                }

                return resp;
            } catch (Exception e) {
                e.printStackTrace();
                return "Error";
            }
        });
    }

    private static String cosHtml() {
        return "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "<title>COS Operation</title>" +
                "</head>" +
                "<body>" +
                "<div class=\"container\">" +
                "<h1>COS Operation</h1>" +
                "<form id=\"cosForm\">" +
                "<label for=\"num1\">Enter the number:</label>" +
                "<input type=\"text\" id=\"num1\" name=\"num1\" required>" +
                "<button type=\"button\" class=\"calc-button\" onclick=\"requestCos()\">Calculate</button>" +
                "</form>" +
                "<div id=\"response\" class=\"response\"></div>" +
                "</div>" +
                "<script>" +
                "function requestCos() {" +
                "const num1 = document.getElementById(\"num1\").value;" +
                "if (!num1) {" +
                "alert(\"Please enter a number.\");" +
                "return;" +
                "}" +
                "const xhttp = new XMLHttpRequest();" +
                "xhttp.onload = function() {" +
                "if (this.status === 200) {" +
                "document.getElementById(\"response\").innerHTML = this.responseText;" +
                "} else {" +
                "document.getElementById(\"response\").innerHTML = \"Error: \" + this.statusText;" +
                "}" +
                "};" +
                "xhttp.onerror = function() {" +
                "document.getElementById(\"response\").innerHTML = \"Request error.\";" +
                "};" +
                "xhttp.open(\"GET\", \"/result?op=cos&num1=\" + num1 + \"&num2=0\");" +
                "xhttp.send();" +
                "}" +
                "</script>" +
                "</body>" +
                "</html>";
    }

    private static String sinHtml() {
        return "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "<title>SIN Operation</title>" +
                "</head>" +
                "<body>" +
                "<div class=\"container\">" +
                "<h1>SIN Operation</h1>" +
                "<form id=\"sinForm\">" +
                "<label for=\"num1\">Enter the number:</label>" +
                "<input type=\"text\" id=\"num1\" name=\"num1\" required>" +
                "<button type=\"button\" class=\"calc-button\" onclick=\"requestSin()\">Calculate</button>" +
                "</form>" +
                "<div id=\"response\" class=\"response\"></div>" +
                "</div>" +
                "<script>" +
                "function requestSin() {" +
                "const num1 = document.getElementById(\"num1\").value;" +
                "if (!num1) {" +
                "alert(\"Please enter a number.\");" +
                "return;" +
                "}" +
                "const xhttp = new XMLHttpRequest();" +
                "xhttp.onload = function() {" +
                "if (this.status === 200) {" +
                "document.getElementById(\"response\").innerHTML = this.responseText;" +
                "} else {" +
                "document.getElementById(\"response\").innerHTML = \"Error: \" + this.statusText;" +
                "}" +
                "};" +
                "xhttp.onerror = function() {" +
                "document.getElementById(\"response\").innerHTML = \"Request error.\";" +
                "};" +
                "xhttp.open(\"GET\", \"/result?op=sin&num1=\" + num1 + \"&num2=0\");" +
                "xhttp.send();" +
                "}" +
                "</script>" +
                "</body>" +
                "</html>";
    }

    private static String magHtml() {
        return "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "<title>Magnitude Operation</title>" +
                "</head>" +
                "<body>" +
                "<div class=\"container\">" +
                "<h1>Magnitude Operation</h1>" +
                "<form id=\"magForm\">" +
                "<label for=\"num1\">Enter the number 1:</label>" +
                "<input type=\"text\" id=\"num1\" name=\"num1\">" +
                "<label for=\"num2\">Enter the number 2:</label>" +
                "<input type=\"text\" id=\"num2\" name=\"num2\">" +
                "<button type=\"button\" class=\"calc-button\" onclick=\"requestMag()\">Calculate</button>" +
                "</form>" +
                "<div id=\"response\" class=\"response\"></div>" +
                "</div>" +
                "<script>" +
                "function requestMag() {" +
                "const num1 = document.getElementById(\"num1\").value;" +
                "const num2 = document.getElementById(\"num2\").value;" +
                "if (!num1 || !num2) {" +
                "alert(\"Please enter both numbers.\");" +
                "return;" +
                "}" +
                "const xhttp = new XMLHttpRequest();" +
                "xhttp.onload = function() {" +
                "if (this.status === 200) {" +
                "document.getElementById(\"response\").innerHTML = this.responseText;" +
                "} else {" +
                "document.getElementById(\"response\").innerHTML = \"Error: \" + this.statusText;" +
                "}" +
                "};" +
                "xhttp.onerror = function() {" +
                "document.getElementById(\"response\").innerHTML = \"Request error.\";" +
                "};" +
                "xhttp.open(\"GET\", \"/result?op=mag&num1=\" + num1 + \"&num2=\" + num2);" +
                "xhttp.send();" +
                "}" +
                "</script>" +
                "</body>" +
                "</html>";
    }

    private static String palindromeHtml() {
        return "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "<title>Palindrome Operation</title>" +
                "</head>" +
                "<body>" +
                "<div class=\"container\">" +
                "<h1>Palindrome Operation</h1>" +
                "<form id=\"palindromeForm\">" +
                "<label for=\"chain\">Enter the string:</label>" +
                "<input type=\"text\" id=\"chain\" name=\"chain\">" +
                "<button type=\"button\" class=\"calc-button\" onclick=\"requestPalindrome()\">Check</button>" +
                "</form>" +
                "<div id=\"response\" class=\"response\"></div>" +
                "</div>" +
                "<script>" +
                "function requestPalindrome() {" +
                "const chain = document.getElementById(\"chain\").value;" +
                "if (!chain) {" +
                "alert(\"Please enter a string.\");" +
                "return;" +
                "}" +
                "const xhttp = new XMLHttpRequest();" +
                "xhttp.onload = function() {" +
                "if (this.status === 200) {" +
                "document.getElementById(\"response\").innerHTML = this.responseText;" +
                "} else {" +
                "document.getElementById(\"response\").innerHTML = \"Error: \" + this.statusText;" +
                "}" +
                "};" +
                "xhttp.onerror = function() {" +
                "document.getElementById(\"response\").innerHTML = \"Request error.\";" +
                "};" +
                "xhttp.open(\"GET\", \"/result?op=palindromo&chain=\" + chain);" +
                "xhttp.send();" +
                "}" +
                "</script>" +
                "</body>" +
                "</html>";
    }


    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
