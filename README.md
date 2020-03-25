# ToyORB
A minimalist toy Object Request Broker.

ToyORB supports the development of distributed object oriented applications.

In the simplified ToyORB model, the remote objects have operations with parameters and return types only int, float and String (no objects). Also, it can't handle concurrent accesses to a remote object.

In order to show the ToyORB implementation is working, two different applications are also developed - InfoServer and MathServer. InfoServer offers operations get_road_info(int road_ID) and get_temp(String city). MathServer offers operations such as do_add(float a, float b) which returns the computed sum and do_sqr(float a) which returns the square root.
