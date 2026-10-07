Requirements
- User requests must be rate limited
- User requests can be rate limited by three ways
  - TimeWindow Base limiting
    System defines a window and in this window user can perform  n requests
    the main issue with this is at the boundary of 2 windows user makes 2X the requests
    so botches the rate limiting all together
  - SlidingWindow based limiting
    Its similar to timeWindow but this time the we do not have multiple time windows, instead we have a single time window that 
    slides, this solves the 2X requests problem
  - TokenBucket based limiting
    each user gets a bucket, this bucket contains tokens and each token is one request so user is limited to how many tokens are 
    there, but there is a cap, bucket has a token refill rate to refill the exhausted tokens

For now, we will keep this configurable and will make sure any of these limiting styles can be implemented, easily to the system.

