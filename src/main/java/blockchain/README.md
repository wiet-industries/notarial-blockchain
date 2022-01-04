
# Blockchain


Every <b>block</b> has list of transaction, it own hash, hash of previous block and creation date (first block - "Gemini" doesn't have previous hash).

<b>Blockchain</b> is simply list of blocks.

![image](https://user-images.githubusercontent.com/72470330/148098422-3a86e479-3cbc-489c-940d-489d36a269d2.png)


<h3> Mem Pool </h3>
Mem pool is a thread safe priority queue where transactions are held. Miner is using mem pool to get transactions with highest priority.
<h3> Miner </h3>
Miner is a class running on another thread. Every time he has the opportunity to create new block, he starts mining. Mining is a process where we create new block by finding adequate nonce number - this process is called <b> proof of work </b>. We use SHA256 algorithm.

<br> <br>
Blockchain and MemPool of every node is held in local database.
<br>

![image](https://user-images.githubusercontent.com/72470330/144079094-deb3d591-76a7-4490-8735-e4d8aa5ffef6.png)
