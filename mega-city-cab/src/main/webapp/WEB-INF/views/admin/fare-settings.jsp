<%@ include file="/WEB-INF/components/admin/check-admin-login.jsp" %>
<%@ include file="/WEB-INF/components/admin/check-super-admin.jsp" %>
<%@ include file="/WEB-INF/components/common-tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Fare Settings</title>
    
    <jsp:include page="/WEB-INF/components/admin/admin-imports.jsp" />
</head>
<body>

	<jsp:include page="/WEB-INF/components/admin/admin-navbar.jsp" />
	<jsp:include page="/WEB-INF/components/toast.jsp" />

	<section class="form-container" style="min-height: 0;">
        <form action="<%= request.getContextPath() %>/admin/fare" method="POST">
            <h3>Update Fare Settings</h3>
            
            <div class="label-container">
				<label>Base Fare</label>
			</div>
            <input type="number" name="base_fare" value="${fareSettings.baseFare}" required class="box" placeholder="Enter base fare" />
            
            <div class="label-container">
				<label>Tax Rate(%)</label>
			</div>
            <input type="number" max="99" step="0.1" name="tax_rate" value="${fareSettings.taxRate}" required class="box" placeholder="Enter tax rate (%)" />
            
            <div class="label-container">
				<label>Discount Rate(%)</label>
			</div>
            <input type="number" max="99" step="0.1" name="discount_rate" value="${fareSettings.discountRate}" required class="box" placeholder="Enter discount rate (%)" />
            
            <input type="submit" value="Save Settings" name="submit" class="btn">
        </form>
    </section>
   	
   	<section>
        <div class="table-container">
            <h1>Fare Settings Update History</h1>
            <div class="table">
                <table>
                    <thead>
                        <tr class="heading">
                            <th>#</th>
                            <th>Base Fare</th>
                            <th>Tax Rate (%)</th>
                            <th>Discount Rate (%)</th>
                            <th>Update Time</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="historyItem" items="${fareSettingsHistory}" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>
                                <td>${historyItem.baseFare}</td>
                                <td>${historyItem.taxRate}</td>
                                <td>${historyItem.discountRate}</td>
                                <td>${historyItem.updateTime}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
	
	<script src="<%= request.getContextPath() %>/assets/js/admin-script.js"></script>
</body>
</html>