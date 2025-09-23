<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background-color: #f2f2f2;
        }

        .profile-box {
            background-color: #00a86b; /* Jade green */
            border: 2px solid #006644; /* Darker green */
            color: white;
            padding: 30px;
            margin: 100px auto;
            width: 300px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
            text-align: center;
        }

        .profile-box h2 {
            margin-bottom: 20px;
        }

        .profile-box p {
            margin: 10px 0;
        }

        .profile-box .label {
            font-weight: bold;
        }

        .profile-box .btn {
            width: 100%;
            padding: 10px;
            background-color: #006644;
            border: none;
            color: white;
            font-weight: bold;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 20px;
        }

        .profile-box .btn:hover {
            background-color: #004d33;
        }
    </style>
</head>
<body>

<div class="profile-box">
    <h2>User Profile</h2>
    <p><span class="label">First Name:</span> ${requestScope.user.firstName}</p>
    <p><span class="label">Last Name:</span> ${requestScope.user.lastName}</p>
    <p><span class="label">Email:</span> ${requestScope.user.email}</p>

    <form action="app/logout" method="post">
        <button type="submit" class="btn">Logout</button>
    </form>
</div>

</body>
</html>
