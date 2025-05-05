<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html lang="en">
<body class="inter antialiased bg-[#0d0e11] text-white">

<main class="pl-64 min-h-screen px-8 py-20 flex items-center justify-center">
    <div class="w-full max-w-3xl bg-[#ebebeb]/5 p-10 rounded-2xl border border-gray-800 shadow-xl space-y-10">

        <div class="text-center">
            <h2 class="text-4xl font-semibold text-[#cff245] tracking-tight mb-4">Update Your Profile</h2>
        </div>

        <c:choose>
            <%@include file="youMustBeLoggedIn.jsp"%>
            <c:otherwise>
                <form action="updateProfile" method="POST" enctype="multipart/form-data" class="space-y-6">

                    <div>
                        <label for="profilePicture" class="block text-lg font-medium text-[#cff245] mb-2">Profile Picture:</label>
                        <input type="file" name="profilePicture" id="profilePicture"
                               class="w-full bg-transparent border border-gray-700 rounded-lg px-4 py-2 text-white placeholder-gray-500 focus:outline-none focus:ring-2 focus:ring-[#cff245]">
                    </div>

                    <div>
                        <label for="name" class="block text-lg font-medium text-[#cff245] mb-2">Name:</label>
                        <input type="text" name="name" id="name" value="${user.name}" required
                               class="w-full bg-transparent border border-gray-700 rounded-lg px-4 py-2 text-white placeholder-gray-500 focus:outline-none focus:ring-2 focus:ring-[#cff245]">
                    </div>

                    <div>
                        <label for="bio" class="block text-lg font-medium text-[#cff245] mb-2">Bio:</label>
                        <textarea name="bio" id="bio" rows="4"
                                  class="w-full bg-transparent border border-gray-700 rounded-lg px-4 py-2 text-white placeholder-gray-500 focus:outline-none focus:ring-2 focus:ring-[#cff245]">${user.bio}</textarea>
                    </div>

                    <div class="text-center">
                        <button type="submit"
                                class="bg-[#cff245] text-black font-semibold py-3 px-8 rounded-xl shadow-lg hover:bg-lime-300 transition-all duration-300">
                            Update Profile
                        </button>
                    </div>

                </form>

                <div class="text-center mt-10 bg-[#cff245]/10 p-6 rounded-xl border border-[#cff245]/30 shadow-inner space-y-4">
                    <p class="text-lg text-gray-300">Current Profile Picture:</p>
                    <img src="<c:out value='${user.profilePicture}'/>" alt="Profile Picture"
                         class="w-24 h-24 rounded-full object-cover shadow-sm mx-auto">
                </div>

                <div class="text-center mt-6 space-y-2">
                    <p>
                        <a href="viewProfile?userId=${user.id}" class="text-[#cff245] hover:underline text-lg">
                            Back to Profile
                        </a>
                    </p>
                    <p>
                        <a href="deleteUser?userId=${user.id}" class="text-red-600 hover:underline text-lg">
                            Delete Profile
                        </a>
                    </p>
                </div>
            </c:otherwise>
        </c:choose>

    </div>
</main>

</body>
</html>
