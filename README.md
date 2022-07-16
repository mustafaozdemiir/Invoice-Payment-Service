# Invoice-Payment-Service

Evam java bootcamp invoice payment service project

# **CUSTOMER**
  ### **{"name": "Mustafa","surname": "ÖZDEMİR"}**
* 1 => <span style="color:green">**[POST]**</span> http://localhost:8080/api/customer/add Adds new customer. (Takes parameters of type customer.) If successful, ResponseEntity<Customer> is returned. If unsuccessful, an error message("Customer not created!") is returned.
* 2 => <span style="color:#30d5c8">**[GET]**</span> http://localhost:8080/api/customer/findByCustomerId?id={id} It brings customers. (Takes the Id parameter.) If successful, ResponseEntity<Customer> is returned. If unsuccessful, an error message("Customer not found!") is returned.
* 3 => <span style="color:#30d5c8">**[GET]**</span> http://localhost:8080/api/customer/findall It brings all the customers. (No parameters are required.) If successful, ResponseEntity<List<Customer>> is returned. If unsuccessful, an error message("Customer not found!") is returned.
* 4 => <span style="color:orange">**[PUT]**</span> http://localhost:8080/api/customer/update?id={id}&name={name}&surname={surname} Updates customer information.(Takes the id, name and surname parameters.) If successful, ResponseEntity<Customer> is returned. If unsuccessful, an error message("Customer not updated!") is returned.
* 5 => <span style="color:red">**[DELETE]**</span> http://localhost:8080/api/customer/deleteById?id={id} The customer deletes. (Takes the Id parameter.) If successful, ResponseEntity<String>("Deleted by {id}") is returned. If unsuccessful, an error message("Customer not deleted!") is returned.

# **PAYMENT**
### **{"date": "2012-12-12","totalAmount": 0}**
* 1 => <span style="color:green">**[POST]**</span> http://localhost:8080/api/payment/add?customerId={customerId} Adds new payment. (Takes parameters of type payment and customerId.) If successful, ResponseEntity<Payment> is returned. If unsuccessful, an error message("Payment not created!") is returned.
* 2 => <span style="color:#30d5c8">**[GET]**</span> http://localhost:8080/api/payment/getByCustomerId?customerId={customerId} It brings payment. (Takes the CustomerId parameter.) If successful, ResponseEntity<Payment> is returned. If unsuccessful, an error message("Payment not found!") is returned.
* 3 => <span style="color:#30d5c8">**[GET]**</span> http://localhost:8080/api/payment/findall It brings all the payments. (No parameters are required.)  If successful, ResponseEntity<List<Payment>> is returned. If unsuccessful, an error message("Payment not found!") is returned.
* 4 => <span style="color:orange">**[PUT]**</span> http://localhost:8080/api/payment/update?id={id}&totalAmount={totalAmount} Updates payment information. (Takes the id and totalAmount parameters.)  If successful, ResponseEntity<Payment> is returned. If unsuccessful, an error message("Payment not updated!") is returned.
* 5 => <span style="color:red">**[DELETE]**</span> http://localhost:8080/api/payment/deleteById?id={id} The payment deletes. (Takes the Id parameter.) If successful, ResponseEntity<String>("Payment deleted. {id}") is returned. If unsuccessful, an error message("Payment not deleted!") is returned.
* 6 => <span style="color:red">**[DELETE]**</span> http://localhost:8080/api/payment/deleteByCustomerId?customerId={customerId} The payment deletes. (Takes the CustomerId parameter.) If successful, ResponseEntity<String>("Payment deleted.") is returned. If unsuccessful, an error message("Payment not deleted! CustomerId=> {customerId}") is returned.

# **INVOICE**
 ### **{"amount": 120,"date": "2010-10-03","status": false}**
* 1 => <span style="color:green">**[POST]**</span> http://localhost:8080/api/invoice/add?customerId={customerId} Adds new invoice. (Takes parameters of type invoice and customerId.) If successful, ResponseEntity<Invoice> is returned. If unsuccessful, an error message("Invoice not created!") is returned.
* 2 => <span style="color:#30d5c8">**[GET]**</span> http://localhost:8080/api/invoice/getByCustomerIdAndInvoiceId?customerId={customerId}&invoiceId={invoiceId} It brings invoice. (Takes the CustomerId and InvoiceId parameters.) If successful, ResponseEntity<Invoice> is returned. If unsuccessful, an error message("Unpaid invoice not found!") is returned.
* 3 => <span style="color:#30d5c8">**[GET]**</span> http://localhost:8080/api/invoice/findall It brings all the invoices. (No parameters are required.) If successful, ResponseEntity<List<Invoice>> is returned. If unsuccessful, an error message("Invoice not found!") is returned.
* 4 => <span style="color:orange">**[PUT]**</span> http://localhost:8080/api/invoice/update?id={id}&amount={amount}&status={status} Updates invoice information. (Takes the id, amount and status parameters.) If successful, ResponseEntity<Invoice> is returned. If unsuccessful, an error message("Invoice not updated!") is returned.
* 5 => <span style="color:orange">**[PUT]**</span> http://localhost:8080/api/invoice/pay?customerId={customerId}&invoiceId={invoiceId} Pays the invoice. (Takes the CustomerId and InvoiceId parameters.) If successful, ResponseEntity<Invoice> is returned. If unsuccessful, an error message("Invoice not found!") is returned. "The invoice has already been paid." is returned if the invoice status is paid.
* 6 => <span style="color:red">**[DELETE]**</span> http://localhost:8080/api/invoice/deleteById?id={id} The invoice deletes. (Takes the Id parameter.) If successful, ResponseEntity<String>("Invoice deleted.") is returned. If unsuccessful, an error message("Invoice not deleted!") is returned.
  