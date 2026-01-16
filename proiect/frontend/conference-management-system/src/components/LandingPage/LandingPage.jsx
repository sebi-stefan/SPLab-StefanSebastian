import React from 'react';
import styles from './LandingPage.module.css';
import {useNavigate} from "react-router-dom";
import {Link} from "react-router-dom";
import "../../index.css";

function LandingPage() {

    const navigate = useNavigate();

    return (
        <div className={"setup-container"}>
            <div className={styles.content}>
                <h1 className={styles.title}>
                    Welcome to ConferenceHub
                </h1>
                <p className={styles.description}>
                    Host, attend and manage your conferences, all in one place!
                </p>
                <div className={styles.buttonContainer}>
                    <Link
                        to={'/login'}
                        className={`landing-page-btn ${styles.btnLogin}`}
                    >Login</Link>

                    <Link
                        to={'register'}
                        className={`landing-page-btn ${styles.btnSignup}`}
                    >Register</Link>
                </div>
            </div>
        </div>
    );
}

export default LandingPage;

