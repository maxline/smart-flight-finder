1. Outbound class format to local date (+)
2. add day of week to the output (+)
3. add sort by date (+)
4. add example using ryanari search api with return flights (roundTripFares) (+)
5. add output formatted summary data for round trip fares
6. add variables dates from - to, add search mode (one way), dates to the name of saved file
7. fix output price with two 00 cents (now only one 0) {'KRK - SKG', flyAt='OCT-30 08:45 Thu', 119.0 PLN, Thessaloniki}
8. fix parsing date times in RyanAirProcessor example
9. add sorting as method parameter, later as api parameter

10. Add logger instead of system.out.println
11. move json parsing examples to unit tests
12. organize and move constants to properties files

13. check other ryanair api (not only fares) that can help to search cheap tickets
14. add finding nearest airports (check also developers.skyscanner api)
15. Add other companies (not only ryanair) fligths (developers.skyscanner api)
16. add friend option (friend from other city that you want flight to some place togather), filtering the flights convenient for both of dates and prices


similar application node.js
https://github.com/2BAD/ryanair


https://www.ryanair.com/hr/en
https://www.ryanair.com/pl/pl
https://www.ryanair.com/pl/en
https://www.ryanair.com/ua/uk