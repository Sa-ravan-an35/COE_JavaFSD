document.getElementById("contactForm").addEventListener("submit", function (e) {
  e.preventDefault();

  let isValid = true;
  const name = document.getElementById("name");
  const email = document.getElementById("email");
  const message = document.getElementById("message");

  const nameError = name.nextElementSibling;
  const emailError = email.nextElementSibling;
  const messageError = message.nextElementSibling;

  nameError.style.visibility = "hidden";
  emailError.style.visibility = "hidden";
  messageError.style.visibility = "hidden";

  if (name.value.trim() === "") {
    nameError.textContent = "Name is required";
    nameError.style.visibility = "visible";
    isValid = false;
  }

  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailPattern.test(email.value.trim())) {
    emailError.textContent = "Enter a valid email";
    emailError.style.visibility = "visible";
    isValid = false;
  }

  if (message.value.trim() === "") {
    messageError.textContent = "Message cannot be empty";
    messageError.style.visibility = "visible";
    isValid = false;
  }

  if (isValid) {
    document.getElementById("formStatus").textContent =
      "Message Sent Successfully!";
    document.getElementById("formStatus").style.color = "#00e5ff";

    // Reset form after successful submission
    setTimeout(() => {
      document.getElementById("contactForm").reset();
      document.getElementById("formStatus").textContent = "";
    }, 3000);
  }
});
