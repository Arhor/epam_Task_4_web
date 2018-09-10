<%--
  Created by IntelliJ IDEA.
  User: Maxim Burishinets
  Date: 08.09.2018
  Time: 3:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="../css/style.css" />
    <title>XML parsers</title>
</head>
<body>
<div class="main">
    <header>
        <h1>${parserType}</h1>
    </header>
    <main>
        <table>
            <thead>
            <tr>
                <th rowspan="3">Type</th>
                <th rowspan="3">Name</th>
                <th rowspan="3">CAS</th>
                <th rowspan="3">Drug Bank</th>
                <th rowspan="3">Pharm</th>
                <th colspan="11">Versions</th>
            </tr>
            <tr>
                <th rowspan="2">Trade name</th>
                <th rowspan="2">Producer</th>
                <th rowspan="2">Form</th>
                <th colspan="3">Certificate</th>
                <th colspan="2">Dosage</th>
                <th colspan="3">Packs</th>
            </tr>
            <tr>
                <th>Registred by</th>
                <th>Registration date</th>
                <th>Expire date</th>
                <th>Amount</th>
                <th>Frequency</th>
                <th>Size</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>
            <tbody>${resultSet}</tbody>
        </table>
    </main>
    <footer>
        <p>Epam, Java WEB-development task - "XML parsing" &copy Maxim Burishinets</p>
    </footer>
</div>
</body>
</html>
