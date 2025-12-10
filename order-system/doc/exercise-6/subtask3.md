# Committing an order

I decided to implement the committing of an order as put method because put is for both creating and updating a
resource.
In this case, commiting an order can be seen as updating the order's status from `IN_PREPARATION` to `COMMITTED`.
The fact that we check the order's status before allowing the commit makes it idempotent.
That's why I chose put over post.
We decided to define it as a purchase of the order to make it resource based, which is why our endpoint is
`/orders/{orderId}/purchase`.
