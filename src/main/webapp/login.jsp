<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Login Page</title>
  <style>
    body {
      background-color: #e8f5f2;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }

    .login-box {
      background-color: #00a86b; /* Jade green */
      border: 2px solid #006644; /* Darker green */
      color: white;
      padding: 30px;
      margin: 100px auto;
      width: 300px;
      border-radius: 10px;
      box-shadow: 0 4px 8px rgba(0,0,0,0.2);
    }

    .login-box h2 {
      text-align: center;
      margin-bottom: 20px;
    }

    .login-box input[type="text"],
    .login-box input[type="password"] {
      width: 100%;
      padding: 10px;
      margin: 10px 0;
      border: none;
      border-radius: 5px;
    }

    .login-box input[type="submit"] {
      width: 20%;
      padding: 10px;
      background-color: #006644;
      border: none;
      color: white;
      font-weight: bold;
      border-radius: 5px;
      cursor: pointer;
    }

    .login-box input[type="submit"]:hover {
      background-color: #004d33;
    }
  </style>
</head>
<body>

  <div class="login-box">
    <h2>Login</h2>
    <form action="/login" method="post">
      <input type="text" name="username" placeholder="Username" required>
      <input type="password" name="password" placeholder="Password" required>
      <input type="submit" value="Login">
    </form>
  </div>


</body>
</html>
