## REST API
Rest Api implemented in Spring used handle communication between frontend and node.


### Endpoints
Documtation so far, endpoints as well as methods might change in future :) 
- `/node/connect` - [POST] - handle connection to core 
- `/node/register` - [POST] - handle node registration on core service
- `/node/broadcast` - [POST] - propagate data to other nodes (it is possible to force this action, but Node will share data by itself)
- `/node/disconnect` - [DELETE] - disconnect from core and stop node service
- `/node/add/transaction` - [POST] - add transaction to node's mempool
- `/node/company/info/{id}` - [GET] - fetch data about company with given id
