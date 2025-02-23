function toggleMenu() {
  const mobileMenu = document.getElementById("mobileMenu");
  const overlay = document.getElementById("overlay");
  const links = document.querySelectorAll(".mobile-links li");

  mobileMenu.classList.toggle("show");
  overlay.classList.toggle("show");

  if (mobileMenu.classList.contains("show")) {
    links.forEach((link, index) => {
      link.style.animation = `slide-in 0.5s ease-in-out forwards ${
        index * 0.2 + 0.2
      }s`;
    });
  } else {
    links.forEach((link) => {
      link.style.animation = "none";
    });
  }
}
