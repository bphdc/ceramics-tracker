<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>

<header class="bg-[#0d0e11] border-b border-gray-800 px-6 py-4 text-white w-full z-50">
    <div class="flex items-center justify-between max-w-7xl mx-auto">

        <!-- logo/home -->
        <a href="index.jsp" class="flex items-center gap-2">
            <i class="bi bi-cup-fill text-xl text-[#cff245]"></i>
            <span class="text-lg font-semibold">Ceramics Studio Tracker</span>
        </a>

        <!-- responsive hamburger -->
        <div class="md:hidden">
            <button id="menu-toggle" class="focus:outline-none text-[#cff245]">
                <i class="bi bi-list text-2xl"></i>
            </button>
        </div>

        <!-- nav -->
        <nav class="hidden md:flex gap-6 text-sm items-center">
            <c:choose>
                <c:when test="${empty userName}">
                    <a href="logIn" class="hover:underline text-[#cff245]">Log In</a>
                </c:when>
                <c:otherwise>
                    <span class="text-gray-400">Hi, <c:out value="${userName}" /></span>

                    <div class="relative">
                        <button data-dropdown-toggle="profile" class="flex items-center gap-1 text-white hover:text-[#cff245]">
                            Profile
                            <i class="bi bi-chevron-down text-xs"></i>
                        </button>
                        <div  id="profile"  data-dropdown-menu="profile"
                              class="hidden absolute left-0 z-10 bg-[#1a1b1e] border border-gray-700 rounded-xl shadow-md mt-2 p-4 space-y-2 w-48">
                            <a href="updateProfile" class="block text-sm text-gray-300 hover:text-white">Update Profile</a>
                            <a href="viewProfile?userId=${userId}" class="block text-sm text-gray-300 hover:text-white">My Profile</a>
                        </div>
                    </div>

                    <div class="relative">
                        <button data-dropdown-toggle="projects" class="flex items-center gap-1 text-white hover:text-[#cff245]">
                            Projects
                            <i class="bi bi-chevron-down text-xs"></i>
                        </button>
                        <div id="projects" data-dropdown-menu="projects"
                             class="hidden absolute left-0 z-10 bg-[#1a1b1e] border border-gray-700 rounded-xl shadow-md mt-2 p-4 space-y-2 w-48">
                            <a href="addProject" class="block text-sm text-gray-300 hover:text-white">Add Project</a>
                            <a href="searchProjects?searchType=myProjects" class="block text-sm text-gray-300 hover:text-white">My Projects</a>
                            <a href="searchProjects" class="block text-sm text-gray-300 hover:text-white">Search Projects</a>
                        </div>
                    </div>

                    <a href="viewAllProfiles" class="hover:text-[#cff245]">Members</a>
                    <a href="generateAIImage" class="hover:text-[#cff245]">Get Inspo</a>

                    <c:if test="${userRole == 'admin'}">
                        <div class="relative">
                            <button data-dropdown-toggle="admin" class="flex items-center gap-1 text-white hover:text-[#cff245]">
                                Admin
                                <i class="bi bi-chevron-down text-xs"></i>
                            </button>
                            <div id="admin" data-dropdown-menu="admin"
                                 class="hidden absolute left-0 z-10 bg-[#1a1b1e] border border-gray-700 rounded-xl shadow-md mt-2 p-4 space-y-2 w-48">
                                <a href="glazeLibrary" class="block text-sm text-green-300 hover:text-white">Glaze Library</a>
                                <a href="tagLibrary" class="block text-sm text-green-300 hover:text-white">Tag Library</a>
                            </div>
                        </div>
                    </c:if>

                    <script>
                        function setupDropdowns() {
                            const dropdownToggles = document.querySelectorAll('[data-dropdown-toggle]');

                            dropdownToggles.forEach(toggle => {
                                const dropdownId = toggle.dataset.dropdownToggle;
                                const dropdownMenu = document.getElementById(dropdownId);
                                console.log("this it the toggle: " + toggle)
                                console.log("this it the id: " + dropdownId)
                                console.log("this is the query selector " + document.querySelector(`[data-dropdown-menu="${dropdownId}"]`) )
                                console.log("this is the dropdownMenu " +dropdownMenu)

                                if (toggle && dropdownMenu) {
                                    toggle.addEventListener('click', () => {
                                        console.log("click1")
                                        dropdownMenu.classList.toggle('hidden');
                                    });
                                }
                            });

                            // close dropdowns
                            document.addEventListener('click', (event) => {
                                console.log("click2")
                                dropdownToggles.forEach(toggle => {
                                    const dropdownId = toggle.dataset.dropdownToggle;
                                    const dropdownMenu = document.getElementById(dropdownId);
                                    if (dropdownMenu && !toggle.contains(event.target) && !dropdownMenu.contains(event.target)) {
                                        dropdownMenu.classList.add('hidden');
                                    }
                                });
                            });
                        }

                        setupDropdowns();
                    </script>

                    <a href="logout" class="text-red-500 hover:text-white">Log Out</a>
                </c:otherwise>
            </c:choose>
        </nav>
    </div>

    <!-- mobile -->
    <div id="mobile-menu" class="md:hidden hidden mt-4 px-4 space-y-2">
        <c:choose>
            <c:when test="${empty userName}">
                <a href="logIn" class="block text-[#cff245]">Log In</a>
            </c:when>
            <c:otherwise>
                <div class="space-y-2 text-sm text-gray-300">
                    <p>Hi, <c:out value="${userName}" /></p>
                    <a href="updateProfile" class="block hover:text-white">Update Profile</a>
                    <a href="viewProfile?userId=${userId}" class="block hover:text-white">My Profile</a>
                    <a href="addProject" class="block hover:text-white">Add Project</a>
                    <a href="searchProjects?searchType=myProjects" class="block hover:text-white">My Projects</a>
                    <a href="searchProjects" class="block hover:text-white">Search Projects</a>
                    <a href="viewAllProfiles" class="block hover:text-white">Members</a>
                    <a href="generateAIImage" class="block hover:text-white">Get Inspiration</a>
                    <c:if test="${userRole == 'admin'}">
                        <a href="glazeLibrary" class="block text-green-300 hover:text-white">Glaze Library</a>
                        <a href="tagLibrary" class="block text-green-300 hover:text-white">Tag Library</a>
                    </c:if>
                    <a href="logout" class="block text-red-500 hover:text-white">Log Out</a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <script>document.addEventListener('DOMContentLoaded', () => {
        const menuToggle = document.getElementById('menu-toggle');
        const mobileMenu = document.getElementById('mobile-menu');

        if (menuToggle && mobileMenu) {
            menuToggle.addEventListener('click', () => {
                mobileMenu.classList.toggle('hidden');
            });
        }
    });</script>
</header>
