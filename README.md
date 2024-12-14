專案名稱:線上投票系統 online_vote_system

1.clone此專案並建立資料庫，且資料庫命名為online_vote_system
參考SQL語法:
CREATE DATABASE online_vote_system;


2.本系統設定連接MySQL資料庫，請於目錄建立.env檔，並加入以下內容：
DATASOURCE_USERNAME=輸入自己資料庫的帳號
DATASOURCE_PASSWORD=輸入自己資料庫的密碼


3.資料庫的 DDL 和 DML 存放在專案下的\DB 資料夾內提供，請在online_vote_system 資料庫中匯入DDL、DML檔。


4.如使用Postman進行測試，於/postmanTest資料夾內提供測試檔，可下載匯入。
  或者於測試工具上使用以下範例:
A.註冊(POST)
url:
http://localhost:8080/api/register
Body(JSON):
{
    "username": "測試員3號",
    "password": "a20241214"
}

B.登入(POST)
url:
http://localhost:8080/api/login
Body(JSON):
{
    "username": "測試員3號",
    "password": "a20241214"
}

C.查詢投票項目及投票數 (GET)
url:
http://localhost:8080/api/getDatas

D.新增投票項目(POST)
url:
http://localhost:8080/api/add
Body(JSON):
{
    "voteItemName": "延長線"
}

E.刪除投票項目(DELETE)
url:
http://localhost:8080/api/delete
Body(JSON):
{
    "voteItemName": "延長線"
}

F.投票(POST)
url:
http://localhost:8080/api/addRecords
Body(JSON):
{
    "voteItemName": ["主機","滑鼠"]
}

G.登出(POST)
url:
http://localhost:8080/api/logout
Body(JSON):
{
  "username": "測試員3號",
  "password": "a20241214"
}


6.其他注意事項
【註冊】
  A.用戶名稱不得與他人重複
  B.密碼至少包含英文及數字各1碼，總長至少為8碼。

【新增、刪除投票項目】
  A.不可對同一資料重複操作。

【投票】
  A.每位用戶僅可提交一次投票，但可多選。 
  B.登入後才可進行操作。




