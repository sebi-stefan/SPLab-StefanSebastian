import InputAdornment from "@mui/material/InputAdornment";

export function addIcon(Icon, position = "end") {
  return {
    InputProps: {
      [position === "start" ? "startAdornment" : "endAdornment"]: (
        <InputAdornment position={position}>
          <Icon fontSize={"medium"} />
        </InputAdornment>
      ),
    },
  };
}

export const formatDate = (dateStr) => {
  if (!dateStr) return "";
  const date = new Date(dateStr);
  return date.toLocaleDateString("en-US", {
    month: "short",
    day: "numeric",
    year: "numeric",
  });
};
