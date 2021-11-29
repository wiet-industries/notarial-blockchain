# node

single blockchain node


Node is listening on both TCP and UDP ports.

Comuincation protocol schema:
- `ID~[node_id]` - server sending to node its ID
- `REGISTER~[node_id]` - node sending to server UPD datagrams to register its UDP port
- //TODO 


Comunication with Server:

![image](https://user-images.githubusercontent.com/30171233/143587283-26e3750c-433a-442d-ab97-bc88fcca1ef1.png)


Sending to server request to broadcast data:

![image](https://user-images.githubusercontent.com/30171233/143587375-0dec4f69-e80d-435a-b8f4-97b3042c1ae2.png)


Connection with different node:

![image](https://user-images.githubusercontent.com/30171233/143588169-5cd19734-f3cf-4da4-8e74-8491b27c649d.png)
