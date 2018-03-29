<%-- 
    Document   : index
    Created on : Mar 15, 2018, 7:56:37 PM
    Author     : Dusan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <%@include file="../_partials/headStyles.jsp"%>
        <title>Kontakt</title>
    </head>
    <body>
        <%@include file="../_partials/navbar.jsp"%>
        <main id="mainC">
            <form class="mbr-form" action="#" method="post" data-form-title="Mobirise Form"><input type="hidden" data-form-email="true" value="zdGVBcNRThcdWUUfI+3PsLnnLtHc8JIdEhub3sFJZu3mJPkE6oxv5FmU79KVvaLjKSmEfsOBbKYj1Cps8hm2o1hmKtkhiOdHe4oDrPZiHdR0Yvomv7W9ZOtj6J4piMeE">
                <div class="row row-sm-offset">
                    <div class="col-md-4 multi-horizontal">
                        <div class="form-group">
                            <label class="form-control-label mbr-fonts-style display-7" for="name-form1-2w">Ime</label>
                            <input type="text" class="form-control" name="name" data-form-field="Name" required="" id="name-form1-2w">
                        </div>
                    </div>
                    <div class="col-md-4 multi-horizontal" >
                        <div class="form-group">
                            <label class="form-control-label mbr-fonts-style display-7" for="email-form1-2w">Email</label>
                            <input type="email" class="form-control" name="email" data-form-field="Email" required="" id="email-form1-2w">
                        </div>
                    </div>
                    <div class="col-md-4 multi-horizontal" >
                        <div class="form-group">
                            <label class="form-control-label mbr-fonts-style display-7" for="phone-form1-2w">Telefon</label>
                            <input type="tel" class="form-control" name="phone" data-form-field="Phone" id="phone-form1-2w">
                        </div>
                    </div>
                </div>
                <div class="form-group" >
                    <label class="form-control-label mbr-fonts-style display-7" for="message-form1-2w">Poruka</label>
                    <textarea type="text" class="form-control" name="message" rows="7" data-form-field="Message" id="message-form1-2w"></textarea>
                </div>

                <span class="input-group-btn">
                    <button href="" type="submit" class="btn btn-primary btn-form display-4">Posalji</button>
                </span>
            </form>
        </main>
        <%@include file="../_partials/footer.jsp"%>
    </body>
</html>
