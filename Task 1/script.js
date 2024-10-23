function handleFormSubmit(event) {
  event.preventDefault();
  clearErrors();
  const isValid = validateForm();
  const modalTitle = document.getElementById("modal-title");
  const modalContent = document.getElementById("modal-content");
  if (isValid) {
    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const phone = document.getElementById("phone").value.trim();
    const message = document.getElementById("message").value.trim();
    const modal = document.getElementById("hs-scale-animation-modal");
    console.log("Form Data Logging in the console:");
    console.log("Name:", name);
    console.log("Email:", email);
    console.log("Phone:", phone);
    console.log("Message:", message);
    modalTitle.innerHTML = "Form Submitted Successfully";
    modalContent.innerHTML = `<p class="mt-1 text-gray-800 dark:text-neutral-400">
        Thank you for reaching out, ${name}! Your form has been successfully submitted, and our team will be in touch with you shortly.
    </p>`;
    if (modal) {
      modal.classList.remove("hidden");
    }
    document.getElementById("contactForm").reset();
  } else {
    modalTitle.innerHTML = "Form Submission Failed";
    modalContent.innerHTML = `<p class="mt-1 text-red-500 dark:text-neutral-400">
        There were errors in the form submission. Please check the fields and try again.
    </p>`;
    const modal = document.getElementById("hs-scale-animation-modal");
    if (modal) {
      modal.classList.remove("hidden");
    }
  }
}

function clearErrors() {
  document.getElementById("nameError").textContent = "";
  document.getElementById("emailError").textContent = "";
  document.getElementById("phoneError").textContent = "";
  document.getElementById("name").style.borderColor = "";
  document.getElementById("email").style.borderColor = "";
  document.getElementById("phone").style.borderColor = "";
}

function validateForm() {
  const name = document.getElementById("name").value.trim();
  const email = document.getElementById("email").value.trim();
  const phone = document.getElementById("phone").value.trim();
  let isValid = true;

  if (name === "") {
    document.getElementById("nameError").textContent =
      "Please enter your name.";
    document.getElementById("nameError").style.color = "red";
    document.getElementById("name").style.borderColor = "red";
    isValid = false;
  }

  const emailPattern = /^[^@]+@[^@]+\.[a-zA-Z]{2,}$/;
  if (email === "" || !emailPattern.test(email)) {
    document.getElementById("emailError").textContent =
      "Please enter a valid email address. e.g(xyz@gmail.com)";
    document.getElementById("emailError").style.color = "red";
    document.getElementById("email").style.borderColor = "red";
    isValid = false;
  }

  if (phone === "" || phone.length < 10 || phone.length > 13) {
    document.getElementById("phoneError").textContent =
      "Please enter a valid phone number (10-13 digits).";
    document.getElementById("phoneError").style.color = "red";
    document.getElementById("phone").style.borderColor = "red";
    isValid = false;
  }

  return isValid;
}

function closeModal() {
  const modal = document.getElementById("hs-scale-animation-modal");
  if (modal) {
    modal.classList.add("hidden");
  }
}
