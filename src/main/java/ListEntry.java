import com.opencsv.bean.CsvBindByName;


public class ListEntry {
    @CsvBindByName(column = "Web ID")
    public String webId;

    @CsvBindByName(column = "Region")
    public String region;

    @CsvBindByName(column = "Reported Date")
    public String reportedDate;

    @CsvBindByName(column = "Reported Year")
    public String reportedYear;

    @CsvBindByName(column = "Reported Month")
    public String reportedMonth;

    @CsvBindByName(column = "Number Dead")
    public String numDead;

    @CsvBindByName(column = "Minimum Estimated Number of Missing")
    public String minimumEstimatedNumberMissing;

    @CsvBindByName(column = "Total Dead and Missing")
    public String totalDeadAndMissing;

    @CsvBindByName(column = "Number of Survivors")
    public String numSurvivors;

    @CsvBindByName(column = "Number of Females")
    public String numFemale;

    @CsvBindByName(column = "Number of Males")
    public String numMale;

    @CsvBindByName(column = "Number of Children")
    public String numChildren;

    @CsvBindByName(column = "Cause of Death")
    public String causeOfDeath;

    @CsvBindByName(column = "Location Description")
    public String location;

    @CsvBindByName(column = "Information Source")
    public String source;

    @CsvBindByName(column = "Location Coordinates")
    public String coordinates;

    @CsvBindByName(column = "Migration Route")
    public String migrationRoute;

    @CsvBindByName(column = "URL")
    public String url;

    @CsvBindByName(column = "UNSD Geographical Grouping")
    public String unsdGeoGrouping;

    @CsvBindByName(column = "Source Quality")
    public String sourceQuality;

}