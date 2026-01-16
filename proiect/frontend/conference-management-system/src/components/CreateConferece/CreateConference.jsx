import TextField from "@mui/material/TextField";
import { Button } from "@mui/material";
import { styled } from "@mui/material/styles";

import { CalendarMonth } from "@mui/icons-material";
import BusinessIcon from "@mui/icons-material/Business";
import LocationPinIcon from "@mui/icons-material/LocationPin";
import GroupsIcon from "@mui/icons-material/Groups";
import DescriptionIcon from "@mui/icons-material/Description";
import CreateIcon from "@mui/icons-material/Create";
import LinkIcon from "@mui/icons-material/Link";
import CloudUploadIcon from "@mui/icons-material/CloudUpload";

import { addIcon } from "../../utils/utils";
import "./Conference.css";

import DatePicker from "react-datepicker";
import CreateSession from "./CreateSession";
import { useEffect, useState } from "react";
import { saveConference } from "../../api/conferenceService";
import conference from "./Conference";
import { editConference } from "../../api/conferenceService";

const VisuallyHiddenInput = styled("input")({
  clip: "rect(0 0 0 0)",
  clipPath: "inset(50%)",
  height: 1,
  overflow: "hidden",
  position: "absolute",
  bottom: 0,
  left: 0,
  whiteSpace: "nowrap",
  width: 1,
});

function CreateConference({
  isSessionOpen,
  setIsSessionOpen,
  sessions,
  setOpenSessionData,
  setEditingIndex,
  closeConferenceModal,
  setConferences,
  fetchConferences,
  isConferenceCreateModalOpen,
  setIsEditModalOpen,
  setIsViewModalOpen,
  setIsConferenceModalOpen,
  conferenceData,
  selectedConference,
}) {
  console.log("CreateConference rendered, conferenceData:", conferenceData);

  const [conferenceDataLocal, setConferenceDataLocal] = useState({
    title: "",
    startDate: "",
    endDate: "",
    venueName: "",
    venueAddress: "",
    maxAttendees: "",
    registrationDeadline: "",
    websiteUrl: "",
    description: "",
  });

  // setConferenceDataLocal(conferenceData);
  // console.log(conferenceData);

  // console.log("CONFERENCE DATAAAAAA", conferenceData);

  useEffect(() => {
    if (conferenceData) {
      setConferenceDataLocal({
        title: conferenceData.title || "",
        startDate: conferenceData.startDate || "",
        endDate: conferenceData.endDate || "",
        venueName: conferenceData.venueName || "",
        venueAddress: conferenceData.venueAddress || "",
        maxAttendees: conferenceData.maxAttendees || "",
        registrationDeadline: conferenceData.registrationDeadline || "",
        websiteUrl: conferenceData.websiteUrl || "",
        description: conferenceData.description || "",
      });
    }
  }, [conferenceData]);

  const [error, setError] = useState("");
  const [shake, setShake] = useState(false);

  function handleCancel() {
    setIsEditModalOpen(false);
    setIsViewModalOpen(true);
    setIsConferenceModalOpen(false);
  }

  function handleChange(e) {
    e.preventDefault();
    const { name, value } = e.target;
    setConferenceDataLocal((prevState) => {
      return {
        ...prevState,
        [name]: value,
      };
    });
  }

  function validateConferenceForm() {
    if (Object.values(conferenceDataLocal).includes("")) {
      setError("Please complete all the fields!");
      return 0;
    }

    const startDate = new Date(conferenceDataLocal.startDate);
    const endDate = new Date(conferenceDataLocal.endDate);
    const registrationDeadline = new Date(
      conferenceDataLocal.registrationDeadline,
    );

    if (startDate > endDate) {
      setError("The start date should be before the end date!");
      return 0;
    }

    if (startDate <= registrationDeadline) {
      setError("The registration deadline should be before the start date!");
      return 0;
    }
    return 1;
  }

  async function handleSaveConference() {
    if (validateConferenceForm() === 0) {
      setShake(true);
      setTimeout(() => setShake(false), 500);
      setTimeout(() => setError(""), 1000);
      return;
    }

    try {
      if (conferenceData && conferenceData.id) {
        // EDITING existing conference
        const updatedData = await editConference(
          conferenceData.id,
          conferenceDataLocal,
        );
        console.log("Conference updated successfully");

        // Fetch fresh data first
        await fetchConferences();

        // Then close modals
        setIsConferenceModalOpen(false);
        setIsEditModalOpen(false);
        setIsViewModalOpen(false);
      } else {
        // CREATING new conference
        const data = await saveConference(conferenceDataLocal);
        console.log("Conference created successfully with ID:", data.id);

        await fetchConferences();
        closeConferenceModal();
      }
    } catch (error) {
      console.error("Failed to save conference:", error);
      setError("Failed to save conference. Please try again.");
      setShake(true);
      setTimeout(() => setShake(false), 500);
      setTimeout(() => setError(""), 3000);
    }
  }

  return (
    <div className={"create-conf-box"}>
      {/*{error !== "" && (*/}
      {/*  <p className={`session-error ${shake ? "shake" : ""}`}>{error}</p>*/}
      {/*)}*/}
      <p className={`session-error ${shake ? "shake" : ""}`}>{error}</p>
      <h1>
        {isConferenceCreateModalOpen
          ? "Edit your conference!"
          : "Create your conference!"}
      </h1>
      <form
        className={"create-conference-form"}
        noValidate={false}
        autoComplete={"off"}
      >
        <div className={"pair"}>
          <TextField
            label="Title"
            name={"title"}
            value={conferenceDataLocal.title}
            {...addIcon(CreateIcon)}
            fullWidth={true}
            autoFocus={true}
            onChange={handleChange}
          />

          {/*<Button*/}
          {/*  component="label"*/}
          {/*  role={undefined}*/}
          {/*  variant="contained"*/}
          {/*  tabIndex={-1}*/}
          {/*  size={"small"}*/}
          {/*  startIcon={<CloudUploadIcon />}*/}
          {/*>*/}
          {/*  Upload Image*/}
          {/*  <VisuallyHiddenInput*/}
          {/*    type="file"*/}
          {/*    onChange={(event) => console.log(event.target.files)}*/}
          {/*    multiple*/}
          {/*  />*/}
          {/*</Button>*/}
        </div>

        <div className={"pair"}>
          <TextField
            fullWidth={true}
            type={"date"}
            name={"startDate"}
            value={conferenceDataLocal.startDate}
            label={"Start Date"}
            onChange={handleChange}
            slotProps={{
              inputLabel: {
                shrink: true,
              },
            }}
          />

          <TextField
            label="End Date"
            name={"endDate"}
            value={conferenceDataLocal.endDate}
            fullWidth={true}
            onChange={handleChange}
            type={"date"}
            slotProps={{
              inputLabel: {
                shrink: true,
              },
            }}
          />
        </div>

        {/*<div className={"pair"}>*/}
        {/*  <TextField*/}
        {/*    label="Country"*/}
        {/*    name={"country"}*/}
        {/*    onChange={handleChange}*/}
        {/*    value={conferenceDataLocal.country}*/}
        {/*    fullWidth={true}*/}
        {/*    {...addIcon(LocationPinIcon)}*/}
        {/*  />*/}

        {/*  <TextField*/}
        {/*    label="City"*/}
        {/*    name={"city"}*/}
        {/*    onChange={handleChange}*/}
        {/*    value={conferenceDataLocal.city}*/}
        {/*    fullWidth={true}*/}
        {/*    {...addIcon(LocationPinIcon)}*/}
        {/*  />*/}
        {/*</div>*/}

        <div className={"pair"}>
          <TextField
            label="Location Name"
            name={"venueName"}
            onChange={handleChange}
            value={conferenceDataLocal.venueName}
            fullWidth={true}
            {...addIcon(BusinessIcon)}
          />

          <TextField
            label="Location Address"
            name={"venueAddress"}
            onChange={handleChange}
            value={conferenceDataLocal.venueAddress}
            fullWidth={true}
            {...addIcon(LocationPinIcon)}
          />
        </div>

        <div className={"pair"}>
          <TextField
            label={"Maximum Attendees"}
            name={"maxAttendees"}
            onChange={handleChange}
            value={conferenceDataLocal.maxAttendees}
            fullWidth={true}
            type={"number"}
            {...addIcon(GroupsIcon)}
          />

          <TextField
            label={"Registration Deadline"}
            name={"registrationDeadline"}
            value={conferenceDataLocal.registrationDeadline}
            fullWidth={true}
            onChange={handleChange}
            type={"date"}
            slotProps={{
              inputLabel: {
                shrink: true,
              },
            }}
          />
        </div>

        <TextField
          label="Website"
          type={"url"}
          onChange={handleChange}
          {...addIcon(LinkIcon)}
          name={"websiteUrl"}
          value={conferenceDataLocal.websiteUrl}
        />

        <TextField
          label="Description"
          name={"description"}
          onChange={handleChange}
          value={conferenceDataLocal.description}
          multiline
          {...addIcon(DescriptionIcon)}
        />
      </form>
      {/*<p>*/}
      {/*  {`Current Sessions added (click to edit): `}*/}
      {/*  {sessions.length*/}
      {/*    ? sessions.map((session, index) => (*/}
      {/*        <span*/}
      {/*          key={index}*/}
      {/*          onClick={() => editSession(session, index)}*/}
      {/*        >{`${session.title} `}</span>*/}
      {/*      ))*/}
      {/*    : ""}*/}
      {/*</p>*/}
      <div className={"btns-session"}>
        <button className={"btn"} onClick={handleSaveConference}>
          {isConferenceCreateModalOpen ? "Save Changes" : "Save"}
        </button>

        {isConferenceCreateModalOpen && (
          <button className={"btn-cancel"} onClick={handleCancel}>
            Cancel
          </button>
        )}
      </div>
    </div>
  );
}

export default CreateConference;
