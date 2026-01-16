import { formatDate } from "../utils/utils";
import { Calendar, MapPin, Users, Globe, Clock } from "lucide-react";
import "./ViewConference.css";
import banner from "../media/banner.jpg";
import SessionCard from "./SessionCard";
import { useEffect, useMemo, useState } from "react";
import conference from "./CreateConferece/Conference";

const ViewConference = ({
  currentUserRole,
  selectedConference,
  onEditConference,
  onDeleteConference,
  onAddSession,
  onEditSession,
  onDeleteSession,
  fetchConferences,
  conferences,
  setEditConference,
}) => {
  const [sampleSessions] = useState([
    {
      id: 1,
      title: "Opening Keynote: Future of Technology",
      description:
        "Join us for an inspiring keynote address exploring the latest trends and innovations shaping our technological future.",
      startTime: "2025-03-13",
      endTime: "2025-03-13",
      room: "Main Auditorium",
      maxAttendees: 500,
    },
    {
      id: 2,
      title: "Workshop: AI and Machine Learning Fundamentals",
      description:
        "Hands-on workshop covering the basics of AI and machine learning with practical examples and exercises.",
      startTime: "2025-03-14",
      endTime: "2025-03-14",
      room: "Conference Room A",
      maxAttendees: 50,
    },
    {
      id: 3,
      title: "Panel Discussion: Ethics in Technology",
      description:
        "Expert panel discussing the ethical implications of emerging technologies and their impact on society.",
      startTime: "2025-03-14",
      endTime: "2025-03-14",
      room: "Conference Room B",
      maxAttendees: 100,
    },
  ]);

  console.log(selectedConference);

  const sessions = selectedConference?.sessions || [];

  const handleEditSession = (session) => {
    if (onEditSession) {
      onEditSession(session);
    }
  };

  const handleDeleteSession = (session) => {
    if (
      window.confirm(
        `Are you sure you want to delete the session "${session.title}"?`,
      )
    ) {
      if (onDeleteSession) {
        onDeleteSession(session);
      }
    }
  };

  const handleDeleteConference = () => {
    if (
      window.confirm(
        `Are you sure you want to delete the conference "${selectedConference.title}"? This action cannot be undone.`,
      )
    ) {
      if (onDeleteConference) {
        onDeleteConference(selectedConference);
      }
    }
  };

  return (
    <div className="view-conference-container">
      <div className="view-conference-banner-wrapper">
        <img
          src={selectedConference.coverImageUrl || banner}
          alt={selectedConference.title}
          className="view-conference-banner"
        />
      </div>

      <div className="view-conference-header">
        <div className="view-conference-title-row">
          <h1 className="view-conference-title">{selectedConference.title}</h1>
          <span className="view-conference-status-tag">
            {/* {formatStatusAndTags(selectedConference.status)} */}
          </span>
        </div>

        <h2 className="view-conference-organizer">
          Hosted by {selectedConference.organizer.firstName}{" "}
          {selectedConference.organizer.lastName}
        </h2>

        <div className="view-conference-tags">
          {/* {getTags(selectedConference).map((tag, index) => (
            <span key={index} className="view-conference-tag">
              {tag}
            </span>
          ))} */}
        </div>
      </div>

      <div className="view-conference-description">
        <p>{selectedConference.description}</p>
      </div>

      <div className="view-conference-info-container">
        <div className="view-container-info-item">
          <Calendar className="view-conference-icon" />
          <div className="view-conference-text-wrapper">
            <span className="view-conference-label">Event Dates</span>
            <span className="view-conference-text">
              {formatDate(selectedConference.startDate)} -{" "}
              {formatDate(selectedConference.endDate)}
            </span>
          </div>
        </div>

        <div className="view-container-info-item">
          <MapPin className="view-conference-icon" />
          <div className="view-conference-text-wrapper">
            <span className="view-conference-label">Venue</span>
            <span className="view-conference-text">
              {selectedConference.venueName}
            </span>
            <span className="view-conference-subtext">
              {selectedConference.venueAddress}
            </span>
          </div>
        </div>

        <div className="view-container-info-item">
          <Users className="view-conference-icon" />
          <div className="view-conference-text-wrapper">
            <span className="view-conference-label">Capacity</span>
            <span className="view-conference-text">
              {selectedConference.maxAttendees} attendees
            </span>
          </div>
        </div>

        <div className="view-container-info-item">
          <Clock className="view-conference-icon" />
          <div className="view-conference-text-wrapper">
            <span className="view-conference-label">Registration Deadline</span>
            <span className="view-conference-text">
              {formatDate(selectedConference.registrationDeadline)}
            </span>
          </div>
        </div>

        <div className="view-container-info-item">
          <Globe className="view-conference-icon" />
          <div className="view-conference-text-wrapper">
            <span className="view-conference-label">Website</span>
            <a
              href={selectedConference.websiteUrl}
              target="_blank"
              rel="noopener noreferrer"
              className="view-conference-link"
            >
              Visit Conference Website
            </a>
          </div>
        </div>
      </div>

      {/* Sessions Section */}
      <div className="view-conference-sessions">
        <div className="view-conference-sessions-header">
          <h2 className="view-conference-sessions-title">Sessions</h2>
          {currentUserRole === "organizer" && onAddSession && (
            <button
              className="view-conference-add-session-btn"
              onClick={onAddSession}
            >
              Add Session
            </button>
          )}
        </div>

        {sessions.length > 0 ? (
          <div className="view-conference-sessions-grid">
            {sessions.map((session, index) => (
              <SessionCard
                key={session.id || index}
                session={session}
                mode={currentUserRole === "organizer" ? "edit" : "display"}
                onEdit={handleEditSession}
                onDelete={handleDeleteSession}
              />
            ))}
          </div>
        ) : (
          <div className="view-conference-sessions-empty">
            <p className="view-conference-sessions-empty-title">
              {currentUserRole === "organizer"
                ? "No sessions added yet"
                : "Sessions coming soon"}
            </p>
            <p className="view-conference-sessions-empty-text">
              {currentUserRole === "organizer"
                ? "Click 'Add Session' to create your first session"
                : "Check back later for session details"}
            </p>
          </div>
        )}
      </div>

      {currentUserRole === "organizer" && (
        <div className="view-conference-actions">
          <button
            className="view-conference-action-btn view-conference-action-btn--edit"
            onClick={onEditConference}
          >
            Edit Conference
          </button>
          <button
            className="view-conference-action-btn view-conference-action-btn--delete"
            onClick={handleDeleteConference}
          >
            Delete Conference
          </button>
        </div>
      )}
    </div>
  );
};

export default ViewConference;
