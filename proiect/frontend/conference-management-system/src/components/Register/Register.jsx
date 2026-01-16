import styles from "./Register.module.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import "../../index.css";
import { register } from "../../api/authService";

function Register() {
  const navigate = useNavigate();

  const [userData, setUserData] = useState({
    email: "",
    password: "",
    firstName: "",
    lastName: "",
    bio: "",
    organization: "",
    role: "ORGANIZER",
  });

  const [errors, setErrors] = useState({});

  function handleOnChange(e) {
    e.preventDefault();
    const { name, value } = e.target;
    setUserData((prevState) => {
      return {
        ...prevState,
        [name]: value,
      };
    });
  }

  function validateForm() {
    const newErrors = {};

    if (
      !userData.firstName ||
      userData.firstName.length < 2 ||
      userData.firstName.length > 20
    ) {
      newErrors.firstName =
        "First name must be between 2 and 20 characters long";
    }
    if (
      !userData.lastName ||
      userData.lastName.length < 2 ||
      userData.lastName.length > 255
    ) {
      newErrors.lastName =
        "Last name must be between 2 and 255 characters long";
    }
    if (
      !userData.email ||
      userData.email.length < 10 ||
      userData.email.length > 30 ||
      !userData.email.includes("@")
    ) {
      newErrors.email =
        "Email must be between 10 and 30 characters long and include '@' sign";
    }
    if (
      !userData.password ||
      userData.password.length < 6 ||
      userData.password.length > 100
    ) {
      newErrors.password = "Password must be between 6 and 100 characters long";
    }
    if (userData.bio.length > 500) {
      newErrors.bio = "Bio must not exceed 500 characters";
    }
    if (userData.organization.length > 50) {
      newErrors.organization = "Organization must not exceed 50 characters";
    }
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  }

  async function handleSubmit(e) {
    e.preventDefault();

    if (!validateForm()) return;

    try {
      const response = await register(userData);
      console.log("Registered");
      window.location.href = "/login";
    } catch (err) {
      console.error("Error: ", err);
      setErrors({ submit: "Connection error. Try again..." });
    }
  }

  return (
    <div className={"setup-container"}>
      <div className={"login-register-box"}>
        <h1 className={"setup-title"}>Create Account</h1>
        <form
          className={"login-register-form"}
          onSubmit={handleSubmit}
          noValidate
          autoComplete={"off"}
        >
          <input
            className={"input"}
            placeholder="First Name"
            type="text"
            name="firstName"
            value={userData.firstName}
            onChange={handleOnChange}
          />
          {errors.firstName && (
            <span className={"error"}>{errors.firstName}</span>
          )}

          <input
            className={"input"}
            placeholder="Last Name"
            type="text"
            name="lastName"
            value={userData.lastName}
            onChange={handleOnChange}
          />
          {errors.lastName && (
            <span className={"error"}>{errors.lastName}</span>
          )}

          <input
            className={"input"}
            placeholder="Email"
            type="email"
            name="email"
            value={userData.email}
            onChange={handleOnChange}
          />
          {errors.email && <span className={"error"}>{errors.email}</span>}

          <input
            className={"input"}
            placeholder="Password"
            type="password"
            name="password"
            value={userData.password}
            onChange={handleOnChange}
          />
          {errors.password && (
            <span className={"error"}>{errors.password}</span>
          )}

          <textarea
            className={styles.textarea}
            placeholder="Bio (optional)"
            name="bio"
            rows="4"
            value={userData.bio}
            onChange={handleOnChange}
          />
          {errors.bio && <span className={"error"}>{errors.bio}</span>}

          <input
            className={"input"}
            placeholder="Organization (optional)"
            type="text"
            name="organization"
            value={userData.organization}
            onChange={handleOnChange}
          />
          {errors.organization && (
            <span className={"error"}>{errors.organization}</span>
          )}

          {errors.submit && <span className={"error"}>{errors.submit}</span>}

          <button className={"btn"} type="submit">
            Register
          </button>
        </form>
        <p className={"prompt"}>
          Already have an account?
          <Link to={"/login"} className={"setup-link"}>
            Login
          </Link>
        </p>

        <p className={"prompt"}>
          Want to go back?
          <Link to={"/"} className={"setup-link"}>
            Home
          </Link>
        </p>
      </div>
    </div>
  );
}

export default Register;
