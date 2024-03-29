# Paypal Java Wrapper

[![Discord](https://img.shields.io/discord/416652224505184276?color=%235865F2&label=Join%20My%20Discord)](https://discord.gg/NzgBrqR)

## Information
This wrapper is a Java Wrapper for Paypal's API.
It is a weak alternative for [Paypal's Java Checkout SDK](https://github.com/paypal/Checkout-Java-SDK).
This library is using the following dependencies:
* org.json:json
* org.apache.httpcomponents:httpclient

## Features
* Generate a Access Token through a Client ID and Secret
* Create an Order with multiple items
* Confirm a purchase

## TODO-List
* Possibly make it async

## Using Liel's Paypal Java Wrapper
To use this library you need to fork and compile it. After doing so, simply add the created .JAR file to your project's dependencies and
create an instance of the PaypalWrapper class, providing your AccessToken/Client ID & Secret.
Then, to call a Request, you can use the following code.
```java
try {
  PaypalWrapper paypalWrapper = PaypalWrapper.buildWrapper(Environment.SANDBOX, "Client ID", "Secret");
                
  CreateOrderRequest createOrderRequest = new CreateOrderRequest("Brand Name", "Return URL", "Cancel URL", "First", "Last", "Address 1", "Address 2", "City", "State", "Zip Code", "Country Code");
  createOrderRequest.addProduct(new Product("Category", "ProductId", "Product Name", "Product Description", "Product Price", (Double) <Product Tax Percentage>, (int) Quantity));
  JSONObject createOrderResponse = paypalWrapper.executeRequest(createOrderRequest);

  String transactionId = createOrderResponse.getString("id");
  ConfirmOrderRequest confirmOrderRequest = new ConfirmOrderRequest(transactionId);
  JSONObject confirmOrderResponse = paypalWrapper.executeRequest(confirmOrderRequest);
} catch (RequestExecutionException executionException) {
  executionException.printStackTrace();
}
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)
