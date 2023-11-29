import { isDateMoreThan6MonthsAgo } from "../service/fusion";

describe("shouldMergeCommunities", () => {
  it("should return true if communities should be merged", () => {
    const lastTimeMerged = "2022-03-15T12:00:00.000Z";
    const pruebadate = new Date(lastTimeMerged);
    const result = isDateMoreThan6MonthsAgo(pruebadate);
    expect(result).toBe(true);
  });
});
