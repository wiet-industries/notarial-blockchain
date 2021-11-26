# core

rendezvous server which manage nodes connection


Core is a VPS serwer with public IP, is listening on both TCP and UDP ports.

Comuincation protocol schema:
- `ID~[node_id]` - server sending to node its ID
- `REGISTER~[node_id]` - node sending to server UPD datagrams to register its UPD port
- //TODO 


Comunication with Node:

![image](https://user-images.githubusercontent.com/30171233/143587283-26e3750c-433a-442d-ab97-bc88fcca1ef1.png)


Handling Node to Node connection:

![image](https://user-images.githubusercontent.com/30171233/143587375-0dec4f69-e80d-435a-b8f4-97b3042c1ae2.png)

