<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <c:url value="/font-awesome/css/font-awesome.min.css" var="faURL" />
    <link rel="stylesheet" href=${faURL}>

    <c:url value="/styles.css" var="cssURL" />
    <link rel="stylesheet" href=${cssURL}>

    <!-- <c:url value="/bootstrap/css/bootstrap.min.css" var="bootstrapURL" />
    <link rel="stylesheet" href=${bootstrapURL}>-->

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

    <title>Address Book Application</title>
  </head>
  <body>
  <div class="container-fluid">
      <nav class="navbar navbar-light bg-light justify-content-between">
        <spring:url value="/" var="homeURL" />
        <a href=${homeURL} class="navbar-brand">Address Book</a>
        <form class="form-inline">
           <c:if test="${showHomeBtn == 'Y'}">
            <a href=${homeURL} class="btn btn btn-success"><i class="fa fa-home" aria-hidden="true"></i></a>
            &nbsp;
           </c:if>
           <button id="saveContactBtn" class="btn btn btn-primary" type="button" data-toggle="modal" data-target="#saveContact"><i class="fa fa-plus" aria-hidden="true"></i></button>&nbsp;
           <button class="btn btn-warning" type="button" data-toggle="modal" data-target="#searchContact"><i class="fa fa-search" aria-hidden="true"></i></button>
        </form>
      </nav>

