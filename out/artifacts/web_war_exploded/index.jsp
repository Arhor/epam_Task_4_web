<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/normalize.css"/>
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <title>XML parsers</title>
    </head>
    <body>
        <div class="main">
            <header>
                <h1>XML parsing presentation</h1>
            </header>
            <main>
                <form name="testForm" action="result" method="GET">
                    <label for="parser">Please, choose parsing type: </label>
                    <select id="parser" name="parser">
                        <option value="sax">SAX</option>
                        <option value="dom">DOM</option>
                        <option value="stax">StAX</option>
                    </select>
                    <hr/>
                    <input class="button" type="submit" name="testButton" value="TRY PARSE">
                </form>
            </main>
            <footer>
                <p>Epam, Java WEB-development task - "XML parsing" &copy Maxim Burishinets</p>
            </footer>
        </div>
    </body>
</html>