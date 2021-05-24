1. Create User :
    This API will create the user

curl --location --request POST 'http://localhost:6969/user/create' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=0E7BC4B0BA711B679C949235C8AB595D' \
--data-raw '{
    "firstName" : "Richa",
    "lastName": "Bhuwania"
 }'

 2. Subscribe to a new Product: User can subscribe to one or more product at a time.
  User information will be picked from it's id from the url.
  For new user, a object of business_profile will also be created.
  For existing user, business_profile details will be there at the system, and frontend will send from that
   and list of products that particular user is subscribed to and want to subscribe [ already subscribed detail will
    also be there with the UI, and will send it back.]

curl --location --request POST 'http://localhost:6969/product/subscribe/1' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=0E7BC4B0BA711B679C949235C8AB595D' \
--data-raw '{
    "business_profile": {
        "company_name": "ABC",
        "legal_name": "ABC",
        "business_address": {
            "line1": "xyz",
            "line2": "xyz",
            "city": "KOLKATA",
            "state": "WEST_BENGAL",
            "country": "INDIA"
        },
        "legal_address": {
            "line1": "xyz",
            "line2": "xyz",
            "city": "KOLKATA",
            "state": "WEST_BENGAL",
            "country": "INDIA"
        },
        "tax": "PAN",
        "email": "richabhuwania5@gmail.com",
        "website": "test@xyz.com"
    },
    "products": ["QB_PAYMENT", "QB_PAYROLL"]
}'

3. Update business information through any product: [Since only one copy of business information is stored and that
is shared across every product, so updating through any product will reflect it everywhere].
It's signature is same as subscribe to product, it's just it is PUT request.
Here, the API will internally call the validate API of all the product's subscribed.
If any of the validate API is not reachable, there is a fallback response that will appear after retrying for 5 times
 or if time has exceeded 10 sec.

curl --location --request PUT 'http://localhost:6969/product/subscribe/1' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=0E7BC4B0BA711B679C949235C8AB595D' \
--data-raw '{
    "business_profile": {
        "company_name": "ABC",
        "legal_name": "ABC",
        "business_address": {
            "line1": "xyz",
            "line2": "xyz",
            "city": "KOLKATA",
            "state": "WEST_BENGAL",
            "country": "INDIA"

        },
        "legal_address": {
            "line1": "xyz",
            "line2": "xyz",
            "city": "KOLKATA",
            "state": "WEST_BENGAL",
            "country": "INDIA"
        },
        "tax": "PAN",
        "email": "richabhuwania5@gmail.com",
        "website": "test@xyz.com"
    },
    "products": ["QB_PAYMENT", "QB_PAYROLL"]
}'

