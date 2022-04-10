<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<br/>
<div class="container-fluid">
    <spring:url value="/person/update/${id}" var="saveURL" />
    <form:form modelAttribute="personForm" method="post" action="${saveURL}" cssClass="form" >
      <div class="form-row">
          <div class="form-group col-6">
              <label>First name <span class="error">*</span></label>
              <form:input path="firstName" cssClass="form-control" id="firstName" />

          </div>
          <div class="form-group col-6">
              <label>Last name <span class="error">*</span></label>
              <form:input path="lastName" cssClass="form-control" id="lastName" />

          </div>
      </div>
      <div class="form-row">
        <div class="form-group col-4">
            <label>Email <span class="error">*</span></label>
            <form:input path="emailAddress" cssClass="form-control" id="emailAddress" />

        </div>
        <div class="form-group col-4">
          <label>Mobile <span class="error">*</span></label>
          <form:input path="mobileNumber" cssClass="form-control" id="mobileNumber" />

        </div>
        <div class="form-group col-4">
          <label>Landline</label>
          <form:input path="landLineNumber" cssClass="form-control" id="landLineNumber" />

          <!-- <div *ngIf="submitted && f['email'].errors" class="invalid-feedback">
              <div *ngIf="f['landLine'].errors?.['required']">Landline is required</div>
              <div *ngIf="f['landLine'].errors?.['pattern']">Landline must be a valid number</div>
          </div> -->
        </div>
      </div>
      <div class="form-row">
        <div class="form-group col-6">
          <label>Street / Location <span class="error">*</span></label>
          <form:input path="street" cssClass="form-control" id="street" />

          <!--<input type="text" formControlName="street" class="form-control" [ngClass]="{ 'is-invalid': submitted && f['street'].errors }" />
          <div *ngIf="submitted && f['street'].errors" class="invalid-feedback">
              <div *ngIf="f['street'].errors?.['required']">Street / Location is required</div>
          </div>-->
        </div>
        <div class="form-group col-6">
          <label>City <span class="error">*</span></label>
          <form:input path="city" cssClass="form-control" id="city" />

          <!--<input type="text" formControlName="city" class="form-control" [ngClass]="{ 'is-invalid': submitted && f['city'].errors }" />
          <div *ngIf="submitted && f['city'].errors" class="invalid-feedback">
              <div *ngIf="f['city'].errors?.['required']">City name is required</div>
          </div>-->
        </div>
      </div>
      <div class="form-row">
        <div class="form-group col-4">
          <label>State <span class="error">*</span></label>
          <form:input path="state" cssClass="form-control" id="state" />

          <!--<input type="text" formControlName="state" class="form-control" [ngClass]="{ 'is-invalid': submitted && f['state'].errors }" />
          <div *ngIf="submitted && f['state'].errors" class="invalid-feedback">
              <div *ngIf="f['state'].errors?.['required']">State name is required</div>
          </div>-->
        </div>
        <div class="form-group col-4">
          <label>Country <span class="error">*</span></label>
          <form:input path="country" cssClass="form-control" id="country" />
          <!--<input type="text" formControlName="country" class="form-control" [ngClass]="{ 'is-invalid': submitted && f['country'].errors }" />
          <div *ngIf="submitted && f['country'].errors" class="invalid-feedback">
              <div *ngIf="f['country'].errors?.['required']">Country name is required</div>
          </div>-->
        </div>
        <div class="form-group col-4">
          <label>Zip code <span class="error">*</span></label>
          <form:input path="zipCode" cssClass="form-control" id="zipCode" />
          <!--<input type="text" formControlName="zipCode" class="form-control" [ngClass]="{ 'is-invalid': submitted && f['zipCode'].errors }" maxlength="6"/>
          <div *ngIf="submitted && f['zipCode'].errors" class="invalid-feedback">
              <div *ngIf="f['zipCode'].errors?.['required']">Zip code is required</div>
              <div *ngIf="f['zipCode'].errors?.['pattern']">Zip code be a valid number</div>
              <div *ngIf="f['zipCode'].errors?.['maxlength']">Zip code must be 6 digits</div>
          </div>-->
        </div>
      </div>
      <div class="text-left">
          <button class="btn btn-primary mr-1">Update</button>
          <button class="btn btn-secondary" type="reset">Reset</button>
      </div>
    </form:form>
</div>