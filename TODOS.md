1. Outbound class format to local date (+)
2. add day of week to the output (+)
3. add sort by date (+)
4. fix output price with two 00 cents (now only one 0) {'KRK - SKG', flyAt='OCT-30 08:45 Thu', 119.0 PLN, Thessaloniki}
5. fix parsing date times in RyanAirProcessor example
6. add sorting as method parameter, later as api parameter

7. Add logger instead of system.out.println
8. move json parsing examples to unit tests
9. organize and move constants to properties files

10. add example using ryanari api with return flights (roundTripFares)
11. check other ryanair api (not only fares) that can help to search cheap tickets
12. add finding nearest airports (check also developers.skyscanner api)
13. Add other companies (not only ryanair) fligths (developers.skyscanner api)
14. add friend option (friend from other city that you want flight to some place togather), filtering the flights convenient for both of dates and prices


similar application node.js
https://github.com/2BAD/ryanair


https://www.ryanair.com/hr/en
https://www.ryanair.com/pl/pl
https://www.ryanair.com/pl/en
https://www.ryanair.com/ua/uk