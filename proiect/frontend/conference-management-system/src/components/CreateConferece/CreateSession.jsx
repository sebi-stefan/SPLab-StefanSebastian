import "./Conference.css";
import TextField from "@mui/material/TextField";
import { addIcon } from "../../utils/utils";
import CreateIcon from "@mui/icons-material/Create";
import LocationPinIcon from "@mui/icons-material/LocationPin";
import GroupsIcon from "@mui/icons-material/Groups";
import DescriptionIcon from "@mui/icons-material/Description";
import { useEffect, useState } from "react";

function CreateSession({
  isSessionOpen,
  data,
  editingIndex,
  onSave,
  onCancel,
  fetchConferences,
}) {
  const [sessionData, setSessionData] = useState({
    title: "",
    startTime: "",
    sessionType: "KEYNOTE",
    endTime: "",
    maxParticipants: "",
    room: "",
    description: "",
  });

  const [error, setError] = useState("");
  const [shake, setShake] = useState(false);

  useEffect(() => {
    if (data && isSessionOpen) {
      setSessionData({
        title: data.title || "",
        startTime: data.startTime || "",
        endTime: data.endTime || "",
        maxParticipants: data.maxParticipants || "",
        room: data.room || "",
        description: data.description || "",
      });
    }
  }, [data, isSessionOpen]);

  function validateSessionForm() {
    if (Object.values(sessionData).includes("")) {
      setError("Please complete all the fields!");
      return false;
    }

    const startDate = new Date(sessionData.startTime);
    const endDate = new Date(sessionData.endTime);

    if (startDate > endDate) {
      setError("The start date should be before the end date!");
      return false;
    }
    return true;
  }

  function handleChange(e) {
    e.preventDefault();
    const { name, value } = e.target;
    setSessionData((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  }

  function handleSaveSession() {
    if (!validateSessionForm()) {
      setShake(true);
      setTimeout(() => setShake(false), 500);
      setTimeout(() => setError(""), 3000);
      return;
    }

    if (onSave) {
      onSave(sessionData);
    }
    fetchConferences();
  }

  function handleCancel() {
    if (onCancel) {
      onCancel();
    }
  }

  if (!isSessionOpen) return null;

  return (
    <div className="create-session-box">
      <p className={`session-error ${shake ? "shake" : ""}`}>{error}</p>
      <h1>{editingIndex === null ? "New Session" : `Edit Session`}</h1>
      <form
        className="create-session-form"
        noValidate={false}
        autoComplete="off"
      >
        <div className="pair">
          <TextField
            label="Title"
            name="title"
            value={sessionData.title}
            onChange={handleChange}
            {...addIcon(CreateIcon)}
            fullWidth={true}
          />
        </div>

        <div className="pair">
          <TextField
            fullWidth={true}
            name="startTime"
            value={sessionData.startTime}
            onChange={handleChange}
            type="datetime-local"
            label="Start Date"
            slotProps={{
              inputLabel: {
                shrink: true,
              },
            }}
          />

          <TextField
            label="End Date"
            name="endTime"
            value={sessionData.endTime}
            onChange={handleChange}
            fullWidth={true}
            type="datetime-local"
            slotProps={{
              inputLabel: {
                shrink: true,
              },
            }}
          />
        </div>

        <TextField
          label="Capacity"
          name="maxParticipants"
          value={sessionData.maxParticipants}
          onChange={handleChange}
          fullWidth={true}
          type="number"
          {...addIcon(GroupsIcon)}
        />

        <TextField
          label="Room"
          name="room"
          value={sessionData.room}
          onChange={handleChange}
          {...addIcon(LocationPinIcon)}
        />

        <TextField
          label="Description"
          name="description"
          value={sessionData.description}
          onChange={handleChange}
          multiline
          {...addIcon(DescriptionIcon)}
        />
      </form>
      <div className="btns-session">
        <button className="btn-save-new-conference" onClick={handleSaveSession}>
          {editingIndex === null ? "Save" : "Update"}
        </button>
        <button className="btn-save-new-conference" onClick={handleCancel}>
          Cancel
        </button>
      </div>
    </div>
  );
}

export default CreateSession;
