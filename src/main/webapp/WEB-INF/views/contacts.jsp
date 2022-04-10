<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<br/>
<div class="container-fluid">
    <c:if test="${searchEmpty eq 'Y' and contacts.size() == 0}">
        <h4 style="text-align: center; padding-top: 30px;">No records found matching search criteria.</h4>
    </c:if>
    <c:if test="${searchEmpty ne 'Y' and contacts.size() == 0}">
        <h1 style="text-align: center; padding-top: 30px;">Welcome to Address Book</h1>
    </c:if>
    <c:if test="${contacts.size() > 0}">
    <div class="row">
        <c:forEach items="${contacts}" var="person" >
        <div class="col-sm-3 mt-3">
            <div class="card">
              <h5 class="card-header">${person.firstName} ${person.lastName}</h5>
              <div class="card-body">
                <p class="card-text">
                    <i class="fa fa-address-card-o" aria-hidden="true"></i> &nbsp; ${person.personAddress.street}, ${person.personAddress.city},
                    ${person.personAddress.state} ${person.personAddress.country} - ${person.personAddress.zipCode} <br><br>
                    <i class="fa fa-envelope-o" aria-hidden="true"></i> &nbsp;${person.personContact.emailAddress}<br>
                    <b> <i class="fa fa-mobile fa-2x" aria-hidden="true"></i> &nbsp;${person.personContact.mobileNumber}</b><br>
                    <c:if test="person.personContact.landLineNumber != null">
                        <b><i class="fa fa-phone-square fa-1x" aria-hidden="true"></i> &nbsp;${person.personContact.landLineNumber}</b>
                    </c:if>
                </p>
                <spring:url value="/person/delete/${person.id}" var="deleteURL" />
                <spring:url value="/person/edit/${person.id}" var="editURL" />
                <a href=${editURL} id="edit" data-id=${person.id} class="btn btn-warning">Edit</a>
                <a href=${deleteURL} onClick="return areYouSure()" id="delete" data-id=${person.id} class="btn btn-danger">Delete</a>
              </div>
            </div>
        </div>
        </c:forEach>
    </div>
    </c:if>
</div>


<!-- Save Modal -->
<div class="modal fade" id="saveContact" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Save Contact</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <spring:url value="/person" var="saveURL" />
        <form:form modelAttribute="personForm" method="post" action="${saveURL }" cssClass="form" >
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
              <button class="btn btn-primary mr-1">Save</button>
              <button class="btn btn-secondary" type="reset">Reset</button>
          </div>
        </form:form>
      </div>
    </div>
  </div>
</div>

<!-- Search Modal -->
<div class="modal fade" id="searchContact" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Find contact</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <spring:url value="/person/search" var="searchURL" />
        <form method="post" action="${searchURL}" cssClass="form" >
            <div class="form-row">
              <div class="form-group col-6">
                <input placeholder="Contact name *" type="text" name="personName" class="form-control" />
              </div>
              <div class="form-group col-6">
                <button class="btn btn-primary mr-1">Find</button>
              </div>
            </div>
        </form>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
    function areYouSure() {
        if ( confirm("Are you sure want to delete?") )
            return true;
        else
            return false;
    }
</script>