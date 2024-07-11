<%@page import="model.Resultado"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Url resultado</title>
<link rel="stylesheet">
</head>
<body>

	<h2>URL: ${requestScope.resultado.url }</h2>
	<h2>Temática: ${requestScope.resultado.tematica }</h2>
	<h2>Descripción: ${requestScope.resultado.descripcion }</h2>
	<a href="toInicio">Volver a home</a>

</body>
</html>