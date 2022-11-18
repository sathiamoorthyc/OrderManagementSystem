# **The following are some improvements, that can me made to the existing classes:**

**Order class:**
1) Change this to entity (store the Orders in DB)
2) Replace the getter/setters with lombok annotations
3) Replace the type 'char' for field 'side' with enum


**OrderBook class:**
1) Better exception handling
2) Better logging
3) Define correct/appropriate default values. For ex:  findBidPrice(..) returns 999999.999999, when no match found. Replace this with proper value.


________________________________________________________________________________________________

_I have printed the behaviour of OrderBook's current implementation via log statements in '**Application.java**' class._

**Below are the output for quick look.**

Initial Orders : [{1=Order{id=1, price=12.31, side=B, size=200}, 2=Order{id=2, price=12.31, side=O, size=200}, 3=Order{id=3, price=5.1, side=B, size=300}}]

Orders after modifying order with id[2]: [{1=Order{id=1, price=12.31, side=B, size=500}, 2=Order{id=2, price=12.31, side=O, size=500}, 3=Order{id=3, price=5.1, side=B, size=300}}]

Order{id=1, price=12.31, side=B, size=500}
Order{id=2, price=12.31, side=O, size=500}
Order{id=3, price=5.1, side=B, size=300}

bid price for B, 2--> 12.31

bid price for B, 2--> 999999.999999

bid price for X, 1--> 999999.999999

Total size for B, 1-> 500

Total size for B, 2-> 800

Total size for B, 3-> 800

Total size for B, 4-> 800

Orders for side B -> [Order{id=3, price=5.1, side=B, size=300}, Order{id=1, price=12.31, side=B, size=500}]

Orders for side O -> [Order{id=2, price=12.31, side=O, size=500}]

Orders for side x -> []

Orders after removing order with id[1]: [{2=Order{id=2, price=12.31, side=O, size=500}, 3=Order{id=3, price=5.1, side=B, size=300}}] 