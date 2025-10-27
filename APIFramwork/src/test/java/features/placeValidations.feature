Feature: Validation Place API 

Scenario: Verify if Place is added successfully
 
Given Add Place Payload
| lat        | lng       | accuracy | name            | phoneNo            | address                   | types          | website           | language  |
| -38.383494 | 33.427362 | 50       | Frontline house | (+91) 983 893 3937 | 29, side layout, cohen 09 | shoe park,shop | http://google.com | French-IN |

When user calls "AddPlaceAPI" with Post request
Then API call is success with success code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"


