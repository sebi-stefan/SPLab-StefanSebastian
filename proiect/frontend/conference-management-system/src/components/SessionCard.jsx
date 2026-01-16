import { Calendar, MapPin, Users } from "lucide-react";
import "./SessionCard.css";
import { formatDate } from "../utils/utils";

const SessionCard = ({ session, mode = "display", onEdit, onDelete }) => {
  const formatDateTime = (isoString) => {
    if (!isoString) return "";

    const date = new Date(isoString);

    const dateOptions = {
      year: "numeric",
      month: "short",
      day: "numeric",
    };

    const timeOptions = {
      hour: "2-digit",
      minute: "2-digit",
      hour12: true,
    };

    const formattedDate = date.toLocaleDateString("en-US", dateOptions);
    const formattedTime = date.toLocaleTimeString("en-US", timeOptions);

    return `${formattedDate} ${formattedTime}`;
  };

  return (
    <div className="session-card">
      <div className="session-card__header">
        <h4 className="session-card__title">{session.title}</h4>
        {mode === "edit" && (
          <div className="session-card__actions">
            <button
              className="session-card__btn session-card__btn--edit"
              onClick={() => onEdit(session)}
            >
              Edit
            </button>
            <button
              className="session-card__btn session-card__btn--delete"
              onClick={() => onDelete(session)}
            >
              Delete
            </button>
          </div>
        )}
      </div>

      <p className="session-card__description">{session.description}</p>

      <div className="session-card__info">
        <div className="session-card__info-item">
          <Calendar className="session-card__info-icon" />
          <span className="session-card__info-text">
            {formatDateTime(session.startTime)} -{" "}
            {formatDateTime(session.endTime)}
          </span>
        </div>

        <div className="session-card__info-item">
          <MapPin className="session-card__info-icon" />
          <span className="session-card__info-text">{session.room}</span>
        </div>

        <div className="session-card__info-item">
          <Users className="session-card__info-icon" />
          <span className="session-card__info-text">
            {session.maxParticipants} attendees
          </span>
        </div>
      </div>
    </div>
  );
};

export default SessionCard;
