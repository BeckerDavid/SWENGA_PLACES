package at.fh.swenga.places.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import at.fh.swenga.places.dao.CountryRepository;
import at.fh.swenga.places.dao.JourneyRepository;
import at.fh.swenga.places.dao.PlaceRepository;
import at.fh.swenga.places.dao.RecommendationRepository;
import at.fh.swenga.places.dao.UserCategoryRepository;
import at.fh.swenga.places.dao.UserDao;
import at.fh.swenga.places.dao.UserRepository;
import at.fh.swenga.places.model.CountryModel;
import at.fh.swenga.places.model.JourneyModel;
import at.fh.swenga.places.model.PlaceModel;
import at.fh.swenga.places.model.RecommendationModel;
import at.fh.swenga.places.model.UserCategoryModel;
import at.fh.swenga.places.model.UserModel;

@Controller
public class InitController {

	@Autowired
	UserDao userDao;

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserCategoryRepository userCatRepo;

	@Autowired
	RecommendationRepository recommendationRepository;

	@Autowired
	PlaceRepository placeRepo;

	@Autowired
	JourneyRepository journeyRepo;

	@RequestMapping("/")
	public String loadIndex() {

		List<CountryModel> test = countryRepository.findAll();
		if (test.size() > 0) {
			return "login";
		} else {
			return "forward:init";
		}

	}

	@RequestMapping("/init")
	@Transactional
	public String fillDataBase(Model model) throws ParseException {
		CountryModel country1 = new CountryModel("AF", "AFGHANISTAN");
		CountryModel country2 = new CountryModel("AL", "ALBANIA");
		CountryModel country3 = new CountryModel("DZ", "ALGERIA");
		CountryModel country4 = new CountryModel("AS", "AMERICAN SAMOA");
		CountryModel country5 = new CountryModel("AD", "ANDORRA");
		CountryModel country6 = new CountryModel("AO", "ANGOLA");
		CountryModel country7 = new CountryModel("AI", "ANGUILLA");
		CountryModel country8 = new CountryModel("AQ", "ANTARCTICA");
		CountryModel country9 = new CountryModel("AG", "ANTIGUA AND BARBUDA");
		CountryModel country10 = new CountryModel("AR", "ARGENTINA");
		CountryModel country11 = new CountryModel("AM", "ARMENIA");
		CountryModel country12 = new CountryModel("AW", "ARUBA");
		CountryModel country13 = new CountryModel("AU", "AUSTRALIA");
		CountryModel country14 = new CountryModel("AT", "AUSTRIA");
		CountryModel country15 = new CountryModel("AZ", "AZERBAIJAN");
		CountryModel country16 = new CountryModel("BS", "BAHAMAS");
		CountryModel country17 = new CountryModel("BH", "BAHRAIN");
		CountryModel country18 = new CountryModel("BD", "BANGLADESH");
		CountryModel country19 = new CountryModel("BB", "BARBADOS");
		CountryModel country20 = new CountryModel("BY", "BELARUS");
		CountryModel country21 = new CountryModel("BE", "BELGIUM");
		CountryModel country22 = new CountryModel("BZ", "BELIZE");
		CountryModel country23 = new CountryModel("BJ", "BENIN");
		CountryModel country24 = new CountryModel("BM", "BERMUDA");
		CountryModel country25 = new CountryModel("BT", "BHUTAN");
		CountryModel country26 = new CountryModel("BO", "BOLIVIA");
		CountryModel country27 = new CountryModel("BA", "BOSNIA AND HERZEGOVINA");
		CountryModel country28 = new CountryModel("BW", "BOTSWANA");
		CountryModel country29 = new CountryModel("BV", "BOUVET ISLAND");
		CountryModel country30 = new CountryModel("BR", "BRAZIL");
		CountryModel country31 = new CountryModel("IO", "BRITISH INDIAN OCEAN TERRITORY");
		CountryModel country32 = new CountryModel("BN", "BRUNEI DARUSSALAM");
		CountryModel country33 = new CountryModel("BG", "BULGARIA");
		CountryModel country34 = new CountryModel("BF", "BURKINA FASO");
		CountryModel country35 = new CountryModel("BI", "BURUNDI");
		CountryModel country36 = new CountryModel("KH", "CAMBODIA");
		CountryModel country37 = new CountryModel("CM", "CAMEROON");
		CountryModel country38 = new CountryModel("CA", "CANADA");
		CountryModel country39 = new CountryModel("CV", "CAPE VERDE");
		CountryModel country40 = new CountryModel("KY", "CAYMAN ISLANDS");
		CountryModel country41 = new CountryModel("CF", "CENTRAL AFRICAN REPUBLIC");
		CountryModel country42 = new CountryModel("TD", "CHAD");
		CountryModel country43 = new CountryModel("CL", "CHILE");
		CountryModel country44 = new CountryModel("CN", "PEOPLE'S REPUBLIC OF CHINA");
		CountryModel country45 = new CountryModel("CX", "CHRISTMAS ISLAND");
		CountryModel country46 = new CountryModel("CC", "COCOS (KEELING) ISLANDS");
		CountryModel country47 = new CountryModel("CO", "COLOMBIA");
		CountryModel country48 = new CountryModel("KM", "COMOROS");
		CountryModel country49 = new CountryModel("CG", "CONGO");
		CountryModel country50 = new CountryModel("CD", "CONGO, THE DEMOCRATIC REPUBLIC OF");
		CountryModel country51 = new CountryModel("CK", "COOK ISLANDS");
		CountryModel country52 = new CountryModel("CR", "COSTA RICA");
		CountryModel country53 = new CountryModel("CI", "C�TE D'IVOIRE");
		CountryModel country54 = new CountryModel("HR", "CROATIA");
		CountryModel country55 = new CountryModel("CU", "CUBA");
		CountryModel country56 = new CountryModel("CY", "CYPRUS");
		CountryModel country57 = new CountryModel("CZ", "CZECH REPUBLIC");
		CountryModel country58 = new CountryModel("DK", "DENMARK");
		CountryModel country59 = new CountryModel("DJ", "DJIBOUTI");
		CountryModel country60 = new CountryModel("DM", "DOMINICA");
		CountryModel country61 = new CountryModel("DO", "DOMINICAN REPUBLIC");
		CountryModel country62 = new CountryModel("EC", "ECUADOR");
		CountryModel country63 = new CountryModel("EG", "EGYPT");
		CountryModel country64 = new CountryModel("EH", "WESTERN SAHARA");
		CountryModel country65 = new CountryModel("SV", "EL SALVADOR");
		CountryModel country66 = new CountryModel("GQ", "EQUATORIAL GUINEA");
		CountryModel country67 = new CountryModel("ER", "ERITREA");
		CountryModel country68 = new CountryModel("EE", "ESTONIA");
		CountryModel country69 = new CountryModel("ET", "ETHIOPIA");
		CountryModel country70 = new CountryModel("FK", "FALKLAND ISLANDS (MALVINAS)");
		CountryModel country71 = new CountryModel("FO", "FAROE ISLANDS");
		CountryModel country72 = new CountryModel("FJ", "FIJI");
		CountryModel country73 = new CountryModel("FI", "FINLAND");
		CountryModel country74 = new CountryModel("FR", "FRANCE");
		CountryModel country75 = new CountryModel("GF", "FRENCH GUIANA");
		CountryModel country76 = new CountryModel("PF", "FRENCH POLYNESIA");
		CountryModel country77 = new CountryModel("TF", "FRENCH SOUTHERN TERRITORIES");
		CountryModel country78 = new CountryModel("GA", "GABON");
		CountryModel country79 = new CountryModel("GM", "GAMBIA");
		CountryModel country80 = new CountryModel("GE", "GEORGIA");
		CountryModel country81 = new CountryModel("DE", "GERMANY");
		CountryModel country82 = new CountryModel("GH", "GHANA");
		CountryModel country83 = new CountryModel("GI", "GIBRALTAR");
		CountryModel country84 = new CountryModel("GR", "GREECE");
		CountryModel country85 = new CountryModel("GL", "GREENLAND");
		CountryModel country86 = new CountryModel("GD", "GRENADA");
		CountryModel country87 = new CountryModel("GP", "GUADELOUPE");
		CountryModel country88 = new CountryModel("GU", "GUAM");
		CountryModel country89 = new CountryModel("GT", "GUATEMALA");
		CountryModel country90 = new CountryModel("GN", "GUINEA");
		CountryModel country91 = new CountryModel("GW", "GUINEA-BISSAU");
		CountryModel country92 = new CountryModel("GY", "GUYANA");
		CountryModel country93 = new CountryModel("HT", "HAITI");
		CountryModel country94 = new CountryModel("HM", "HEARD ISLAND AND MCDONALD ISLANDS");
		CountryModel country95 = new CountryModel("HN", "HONDURAS");
		CountryModel country96 = new CountryModel("HK", "HONG KONG");
		CountryModel country97 = new CountryModel("HU", "HUNGARY");
		CountryModel country98 = new CountryModel("IS", "ICELAND");
		CountryModel country99 = new CountryModel("IN", "INDIA");
		CountryModel country100 = new CountryModel("ID", "INDONESIA");
		CountryModel country101 = new CountryModel("IR", "IRAN, ISLAMIC REPUBLIC OF");
		CountryModel country102 = new CountryModel("IQ", "IRAQ");
		CountryModel country103 = new CountryModel("IE", "IRELAND");
		CountryModel country104 = new CountryModel("IL", "ISRAEL");
		CountryModel country105 = new CountryModel("IT", "ITALY");
		CountryModel country106 = new CountryModel("JM", "JAMAICA");
		CountryModel country107 = new CountryModel("JP", "JAPAN");
		CountryModel country108 = new CountryModel("JO", "JORDAN");
		CountryModel country109 = new CountryModel("KZ", "KAZAKHSTAN");
		CountryModel country110 = new CountryModel("KE", "KENYA");
		CountryModel country111 = new CountryModel("KI", "KIRIBATI");
		CountryModel country112 = new CountryModel("KP", "KOREA, DEMOCRATIC PEOPLE'S REPUBLIC OF");
		CountryModel country113 = new CountryModel("KR", "KOREA, REPUBLIC OF");
		CountryModel countryK = new CountryModel("XK", "KOSOVO");
		CountryModel country114 = new CountryModel("KW", "KUWAIT");
		CountryModel country115 = new CountryModel("KG", "KYRGYZSTAN");
		CountryModel country116 = new CountryModel("LA", "LAO PEOPLE'S DEMOCRATIC REPUBLIC");
		CountryModel country117 = new CountryModel("LV", "LATVIA");
		CountryModel country118 = new CountryModel("LB", "LEBANON");
		CountryModel country119 = new CountryModel("LS", "LESOTHO");
		CountryModel country120 = new CountryModel("LR", "LIBERIA");
		CountryModel country121 = new CountryModel("LY", "LIBYAN ARAB JAMAHIRIYA");
		CountryModel country122 = new CountryModel("LI", "LIECHTENSTEIN");
		CountryModel country123 = new CountryModel("LT", "LITHUANIA");
		CountryModel country124 = new CountryModel("LU", "LUXEMBOURG");
		CountryModel country125 = new CountryModel("MO", "MACAO");
		CountryModel country126 = new CountryModel("MK", "MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF");
		CountryModel country127 = new CountryModel("MG", "MADAGASCAR");
		CountryModel country128 = new CountryModel("MW", "MALAWI");
		CountryModel country129 = new CountryModel("MY", "MALAYSIA");
		CountryModel country130 = new CountryModel("MV", "MALDIVES");
		CountryModel country131 = new CountryModel("ML", "MALI");
		CountryModel country132 = new CountryModel("MT", "MALTA");
		CountryModel country133 = new CountryModel("MH", "MARSHALL ISLANDS");
		CountryModel country134 = new CountryModel("MQ", "MARTINIQUE");
		CountryModel country135 = new CountryModel("MR", "MAURITANIA");
		CountryModel country136 = new CountryModel("MU", "MAURITIUS");
		CountryModel country137 = new CountryModel("YT", "MAYOTTE");
		CountryModel country138 = new CountryModel("MX", "MEXICO");
		CountryModel country139 = new CountryModel("FM", "MICRONESIA, FEDERATED STATES OF");
		CountryModel country140 = new CountryModel("MD", "MOLDOVA, REPUBLIC OF");
		CountryModel country141 = new CountryModel("MC", "MONACO");
		CountryModel country142 = new CountryModel("MN", "MONGOLIA");
		CountryModel country143 = new CountryModel("MS", "MONTSERRAT");
		CountryModel country144 = new CountryModel("MA", "MOROCCO");
		CountryModel country145 = new CountryModel("MZ", "MOZAMBIQUE");
		CountryModel country146 = new CountryModel("MM", "MYANMAR");
		CountryModel country147 = new CountryModel("NA", "NAMIBIA");
		CountryModel country148 = new CountryModel("NR", "NAURU");
		CountryModel country149 = new CountryModel("NP", "NEPAL");
		CountryModel country150 = new CountryModel("NL", "NETHERLANDS");
		CountryModel country151 = new CountryModel("AN", "NETHERLANDS ANTILLES");
		CountryModel country152 = new CountryModel("NC", "NEW CALEDONIA");
		CountryModel country153 = new CountryModel("NZ", "NEW ZEALAND");
		CountryModel country154 = new CountryModel("NI", "NICARAGUA");
		CountryModel country155 = new CountryModel("NE", "NIGER");
		CountryModel country156 = new CountryModel("NG", "NIGERIA");
		CountryModel country157 = new CountryModel("NU", "NIUE");
		CountryModel country158 = new CountryModel("NF", "NORFOLK ISLAND");
		CountryModel country159 = new CountryModel("MP", "NORTHERN MARIANA ISLANDS");
		CountryModel country160 = new CountryModel("NO", "NORWAY");
		CountryModel country161 = new CountryModel("OM", "OMAN");
		CountryModel country162 = new CountryModel("PK", "PAKISTAN");
		CountryModel country163 = new CountryModel("PW", "PALAU");
		CountryModel country164 = new CountryModel("PS", "PALESTINIAN TERRITORY, OCCUPIED");
		CountryModel country165 = new CountryModel("PA", "PANAMA");
		CountryModel country166 = new CountryModel("PG", "PAPUA NEW GUINEA");
		CountryModel country167 = new CountryModel("PY", "PARAGUAY");
		CountryModel country168 = new CountryModel("PE", "PERU");
		CountryModel country169 = new CountryModel("PH", "PHILIPPINES");
		CountryModel country170 = new CountryModel("PN", "PITCAIRN");
		CountryModel country171 = new CountryModel("PL", "POLAND");
		CountryModel country172 = new CountryModel("PT", "PORTUGAL");
		CountryModel country173 = new CountryModel("PR", "PUERTO RICO");
		CountryModel country174 = new CountryModel("QA", "QATAR");
		CountryModel country175 = new CountryModel("RE", "RÉUNION");
		CountryModel country176 = new CountryModel("RO", "ROMANIA");
		CountryModel country177 = new CountryModel("RU", "RUSSIAN FEDERATION");
		CountryModel country178 = new CountryModel("RW", "RWANDA");
		CountryModel country179 = new CountryModel("SH", "SAINT HELENA");
		CountryModel country180 = new CountryModel("KN", "SAINT KITTS AND NEVIS");
		CountryModel country181 = new CountryModel("LC", "SAINT LUCIA");
		CountryModel country182 = new CountryModel("PM", "SAINT PIERRE AND MIQUELON");
		CountryModel country183 = new CountryModel("VC", "SAINT VINCENT AND THE GRENADINES");
		CountryModel country184 = new CountryModel("WS", "SAMOA");
		CountryModel country185 = new CountryModel("SM", "SAN MARINO");
		CountryModel country186 = new CountryModel("ST", "SAO TOME AND PRINCIPE");
		CountryModel country187 = new CountryModel("SA", "SAUDI ARABIA");
		CountryModel country188 = new CountryModel("SN", "SENEGAL");
		CountryModel country189 = new CountryModel("CS", "SERBIA AND MONTENEGRO");
		CountryModel country190 = new CountryModel("SC", "SEYCHELLES");
		CountryModel country191 = new CountryModel("SL", "SIERRA LEONE");
		CountryModel country192 = new CountryModel("SG", "SINGAPORE");
		CountryModel country193 = new CountryModel("SK", "SLOVAKIA");
		CountryModel country194 = new CountryModel("SI", "SLOVENIA");
		CountryModel country195 = new CountryModel("SB", "SOLOMON ISLANDS");
		CountryModel country196 = new CountryModel("SO", "SOMALIA");
		CountryModel country197 = new CountryModel("ZA", "SOUTH AFRICA");
		CountryModel country198 = new CountryModel("GS", "SOUTH GEORGIA AND SOUTH SANDWICH ISLANDS");
		CountryModel country199 = new CountryModel("ES", "SPAIN");
		CountryModel country200 = new CountryModel("LK", "SRI LANKA");
		CountryModel country201 = new CountryModel("SD", "SUDAN");
		CountryModel country202 = new CountryModel("SR", "SURINAME");
		CountryModel country203 = new CountryModel("SJ", "SVALBARD AND JAN MAYEN");
		CountryModel country204 = new CountryModel("SZ", "SWAZILAND");
		CountryModel country205 = new CountryModel("SE", "SWEDEN");
		CountryModel country206 = new CountryModel("CH", "SWITZERLAND");
		CountryModel country207 = new CountryModel("SY", "SYRIAN ARAB REPUBLIC");
		CountryModel country208 = new CountryModel("TW", "TAIWAN, REPUBLIC OF CHINA");
		CountryModel country209 = new CountryModel("TJ", "TAJIKISTAN");
		CountryModel country210 = new CountryModel("TZ", "TANZANIA, UNITED REPUBLIC OF");
		CountryModel country211 = new CountryModel("TH", "THAILAND");
		CountryModel country212 = new CountryModel("TL", "TIMOR-LESTE");
		CountryModel country213 = new CountryModel("TG", "TOGO");
		CountryModel country214 = new CountryModel("TK", "TOKELAU");
		CountryModel country215 = new CountryModel("TO", "TONGA");
		CountryModel country216 = new CountryModel("TT", "TRINIDAD AND TOBAGO");
		CountryModel country217 = new CountryModel("TN", "TUNISIA");
		CountryModel country218 = new CountryModel("TR", "TURKEY");
		CountryModel country219 = new CountryModel("TM", "TURKMENISTAN");
		CountryModel country220 = new CountryModel("TC", "TURKS AND CAICOS ISLANDS");
		CountryModel country221 = new CountryModel("TV", "TUVALU");
		CountryModel country222 = new CountryModel("UG", "UGANDA");
		CountryModel country223 = new CountryModel("UA", "UKRAINE");
		CountryModel country224 = new CountryModel("AE", "UNITED ARAB EMIRATES");
		CountryModel country225 = new CountryModel("GB", "UNITED KINGDOM");
		CountryModel country226 = new CountryModel("US", "UNITED STATES OF AMERICA");
		CountryModel country227 = new CountryModel("UM", "UNITED STATES MINOR OUTLYING ISLANDS");
		CountryModel country228 = new CountryModel("UY", "URUGUAY");
		CountryModel country229 = new CountryModel("UZ", "UZBEKISTAN");
		CountryModel country230 = new CountryModel("VE", "VENEZUELA");
		CountryModel country231 = new CountryModel("VU", "VANUATU");
		CountryModel country232 = new CountryModel("VN", "VIET NAM");
		CountryModel country233 = new CountryModel("VG", "BRITISH VIRGIN ISLANDS");
		CountryModel country234 = new CountryModel("VI", "U.S. VIRGIN ISLANDS");
		CountryModel country235 = new CountryModel("WF", "WALLIS AND FUTUNA");
		CountryModel country236 = new CountryModel("YE", "YEMEN");
		CountryModel country237 = new CountryModel("ZW", "ZIMBABWE");

		countryRepository.save(country1);
		countryRepository.save(country2);
		countryRepository.save(country3);
		countryRepository.save(country4);
		countryRepository.save(country5);
		countryRepository.save(country6);
		countryRepository.save(country7);
		countryRepository.save(country8);
		countryRepository.save(country9);
		countryRepository.save(country10);
		countryRepository.save(country11);
		countryRepository.save(country12);
		countryRepository.save(country13);
		countryRepository.save(country14);
		countryRepository.save(country15);
		countryRepository.save(country16);
		countryRepository.save(country17);
		countryRepository.save(country18);
		countryRepository.save(country19);
		countryRepository.save(country20);
		countryRepository.save(country21);
		countryRepository.save(country22);
		countryRepository.save(country23);
		countryRepository.save(country24);
		countryRepository.save(country25);
		countryRepository.save(country26);
		countryRepository.save(country27);
		countryRepository.save(country28);
		countryRepository.save(country29);
		countryRepository.save(country30);
		countryRepository.save(country31);
		countryRepository.save(country32);
		countryRepository.save(country33);
		countryRepository.save(country34);
		countryRepository.save(country35);
		countryRepository.save(country36);
		countryRepository.save(country37);
		countryRepository.save(country38);
		countryRepository.save(country39);
		countryRepository.save(country40);
		countryRepository.save(country41);
		countryRepository.save(country42);
		countryRepository.save(country43);
		countryRepository.save(country44);
		countryRepository.save(country45);
		countryRepository.save(country46);
		countryRepository.save(country47);
		countryRepository.save(country48);
		countryRepository.save(country49);
		countryRepository.save(country50);
		countryRepository.save(country51);
		countryRepository.save(country52);
		countryRepository.save(country53);
		countryRepository.save(country54);
		countryRepository.save(country55);
		countryRepository.save(country56);
		countryRepository.save(country57);
		countryRepository.save(country58);
		countryRepository.save(country59);
		countryRepository.save(country60);
		countryRepository.save(country61);
		countryRepository.save(country62);
		countryRepository.save(country63);
		countryRepository.save(country64);
		countryRepository.save(country65);
		countryRepository.save(country66);
		countryRepository.save(country67);
		countryRepository.save(country68);
		countryRepository.save(country69);
		countryRepository.save(country70);
		countryRepository.save(country71);
		countryRepository.save(country72);
		countryRepository.save(country73);
		countryRepository.save(country74);
		countryRepository.save(country75);
		countryRepository.save(country76);
		countryRepository.save(country77);
		countryRepository.save(country78);
		countryRepository.save(country79);
		countryRepository.save(country80);
		countryRepository.save(country81);
		countryRepository.save(country82);
		countryRepository.save(country83);
		countryRepository.save(country84);
		countryRepository.save(country85);
		countryRepository.save(country86);
		countryRepository.save(country87);
		countryRepository.save(country88);
		countryRepository.save(country89);
		countryRepository.save(country90);
		countryRepository.save(country91);
		countryRepository.save(country92);
		countryRepository.save(country93);
		countryRepository.save(country94);
		countryRepository.save(country95);
		countryRepository.save(country96);
		countryRepository.save(country97);
		countryRepository.save(country98);
		countryRepository.save(country99);
		countryRepository.save(country100);
		countryRepository.save(country101);
		countryRepository.save(country102);
		countryRepository.save(country103);
		countryRepository.save(country104);
		countryRepository.save(country105);
		countryRepository.save(country106);
		countryRepository.save(country107);
		countryRepository.save(country108);
		countryRepository.save(country109);
		countryRepository.save(country110);
		countryRepository.save(country111);
		countryRepository.save(country112);
		countryRepository.save(country113);
		countryRepository.save(countryK);
		countryRepository.save(country114);
		countryRepository.save(country115);
		countryRepository.save(country116);
		countryRepository.save(country117);
		countryRepository.save(country118);
		countryRepository.save(country119);
		countryRepository.save(country120);
		countryRepository.save(country121);
		countryRepository.save(country122);
		countryRepository.save(country123);
		countryRepository.save(country124);
		countryRepository.save(country125);
		countryRepository.save(country126);
		countryRepository.save(country127);
		countryRepository.save(country128);
		countryRepository.save(country129);
		countryRepository.save(country130);
		countryRepository.save(country131);
		countryRepository.save(country132);
		countryRepository.save(country133);
		countryRepository.save(country134);
		countryRepository.save(country135);
		countryRepository.save(country136);
		countryRepository.save(country137);
		countryRepository.save(country138);
		countryRepository.save(country139);
		countryRepository.save(country140);
		countryRepository.save(country141);
		countryRepository.save(country142);
		countryRepository.save(country143);
		countryRepository.save(country144);
		countryRepository.save(country145);
		countryRepository.save(country146);
		countryRepository.save(country147);
		countryRepository.save(country148);
		countryRepository.save(country149);
		countryRepository.save(country150);
		countryRepository.save(country151);
		countryRepository.save(country152);
		countryRepository.save(country153);
		countryRepository.save(country154);
		countryRepository.save(country155);
		countryRepository.save(country156);
		countryRepository.save(country157);
		countryRepository.save(country158);
		countryRepository.save(country159);
		countryRepository.save(country160);
		countryRepository.save(country161);
		countryRepository.save(country162);
		countryRepository.save(country163);
		countryRepository.save(country164);
		countryRepository.save(country165);
		countryRepository.save(country166);
		countryRepository.save(country167);
		countryRepository.save(country168);
		countryRepository.save(country169);
		countryRepository.save(country170);
		countryRepository.save(country171);
		countryRepository.save(country172);
		countryRepository.save(country173);
		countryRepository.save(country174);
		countryRepository.save(country175);
		countryRepository.save(country176);
		countryRepository.save(country177);
		countryRepository.save(country178);
		countryRepository.save(country179);
		countryRepository.save(country180);
		countryRepository.save(country181);
		countryRepository.save(country182);
		countryRepository.save(country183);
		countryRepository.save(country184);
		countryRepository.save(country185);
		countryRepository.save(country186);
		countryRepository.save(country187);
		countryRepository.save(country188);
		countryRepository.save(country189);
		countryRepository.save(country190);
		countryRepository.save(country191);
		countryRepository.save(country192);
		countryRepository.save(country193);
		countryRepository.save(country194);
		countryRepository.save(country195);
		countryRepository.save(country196);
		countryRepository.save(country197);
		countryRepository.save(country198);
		countryRepository.save(country199);
		countryRepository.save(country200);
		countryRepository.save(country201);
		countryRepository.save(country202);
		countryRepository.save(country203);
		countryRepository.save(country204);
		countryRepository.save(country205);
		countryRepository.save(country206);
		countryRepository.save(country207);
		countryRepository.save(country208);
		countryRepository.save(country209);
		countryRepository.save(country210);
		countryRepository.save(country211);
		countryRepository.save(country212);
		countryRepository.save(country213);
		countryRepository.save(country214);
		countryRepository.save(country215);
		countryRepository.save(country216);
		countryRepository.save(country217);
		countryRepository.save(country218);
		countryRepository.save(country219);
		countryRepository.save(country220);
		countryRepository.save(country221);
		countryRepository.save(country222);
		countryRepository.save(country223);
		countryRepository.save(country224);
		countryRepository.save(country225);
		countryRepository.save(country226);
		countryRepository.save(country227);
		countryRepository.save(country228);
		countryRepository.save(country229);
		countryRepository.save(country230);
		countryRepository.save(country231);
		countryRepository.save(country232);
		countryRepository.save(country233);
		countryRepository.save(country234);
		countryRepository.save(country235);
		countryRepository.save(country236);
		countryRepository.save(country237);

		UserCategoryModel admin = new UserCategoryModel("ROLE_ADMIN");
		UserCategoryModel user = new UserCategoryModel("ROLE_USER");
		UserCategoryModel viewer = new UserCategoryModel("ROLE_VIEWER");

		userCatRepo.save(admin);
		userCatRepo.save(user);
		userCatRepo.save(viewer);

		String token0 = UUID.randomUUID().toString();
		String token1 = UUID.randomUUID().toString();
		String token2 = UUID.randomUUID().toString();
		String token3 = UUID.randomUUID().toString();
		String token4 = UUID.randomUUID().toString();

		UserModel admin1 = new UserModel("admin", true, "password", "Robert", "Admin", "robert.admin@boop.fh",
				country44, false, token1);

		admin1.encryptPassword();
		admin1.addUserCategory(admin);
		admin1.addUserCategory(user);

		UserModel user1 = new UserModel("user", true, "password", "Alexander", "User", "alex.ei@nischl.fh", country222,
				false, token0);

		user1.encryptPassword();
		user1.addUserCategory(user);

		UserModel user2 = new UserModel("user2", true, "password", "Benjamin", "User2", "bluemchen@ottos.sklave.fh",
				country14, false, token2);
		user2.encryptPassword();
		user2.addUserCategory(user);

		UserModel defaultUser1 = new UserModel("default", true, "default", "default", "default",
				"default.default@gmx.at", country10, false, token3);
		defaultUser1.encryptPassword();
		defaultUser1.addUserCategory(viewer);

		UserModel viki = new UserModel("Viki", true, "password", "Viki", "Gradwohl", "gradwohl.viktoria@gmx.at",
				country10, false, token4);
		viki.encryptPassword();
		viki.addUserCategory(user);

		userRepository.save(admin1);
		userRepository.save(defaultUser1);
		userRepository.save(user1);
		userRepository.save(user2);

		userRepository.save(viki);

		PlaceModel bangkok = new PlaceModel("Bangkok", country211);
		PlaceModel place1 = new PlaceModel("FH Joanneum", country14);
		PlaceModel place2 = new PlaceModel("Mount Everest", country149);
		PlaceModel place3 = new PlaceModel("Atlantis", country223);
		PlaceModel place4 = new PlaceModel("Pentagon", country226);
		PlaceModel place5 = new PlaceModel("Olymp", country84);

		placeRepo.save(bangkok);
		placeRepo.save(place1);
		placeRepo.save(place2);
		placeRepo.save(place3);
		placeRepo.save(place4);
		placeRepo.save(place5);

		RecommendationModel recommendation1 = new RecommendationModel("Travelling in Thailand", bangkok,
				"Enjoy a trip in one of the most intresting cities in the world!", user1);
		RecommendationModel rec2 = new RecommendationModel("Room 45 (HVSYS) - Birthplace of Places", place1,
				"Since development of Places mainly took place here, it is now a famed pilgrimage site.", admin1);
		RecommendationModel rec3 = new RecommendationModel("Mountaineers rejoice", place2,
				"From up here the world looks so small. #fancy", admin1);
		RecommendationModel rec4 = new RecommendationModel("Diver's Paradise", place3,
				"Can't believe people used to live here. They should have made 'Finding Nemo' here!", admin1);
		RecommendationModel rec5 = new RecommendationModel("Trump's favorite toilet", place4,
				"It even smells like him. SAD.", admin1);
		RecommendationModel rec6 = new RecommendationModel("Zeus throwing lightning", place5,
				"Today we could witness the God of gods' famous lightning-throwing ceremony."
						+ "Note: Tuesday to Sunday the gods drink their Ambrosia and might mistake you for a target. Travel insurance is advisable.",
				admin1);

		recommendationRepository.save(recommendation1);
		recommendationRepository.save(rec2);
		recommendationRepository.save(rec3);
		recommendationRepository.save(rec4);
		recommendationRepository.save(rec5);
		recommendationRepository.save(rec6);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateD = sdf.parse("2019-06-20");
		Calendar calD = Calendar.getInstance();
		calD.setTime(dateD);

		Date dateS = sdf.parse("2019-07-10");
		Calendar calS = Calendar.getInstance();
		calS.setTime(dateS);

		Set<PlaceModel> places = new HashSet<PlaceModel>();
		places.add(bangkok);
		Set<CountryModel> countries = new HashSet<CountryModel>();
		countries.add(countryK);

		JourneyModel journey1 = new JourneyModel(calD, calS, places, user1, new Float(1000), countries, "Pristina",
				10000);
		JourneyModel journey2 = new JourneyModel(calD, calS, places, user2, new Float(1000), countries, "Prizren",
				10000);
		JourneyModel journey3 = new JourneyModel(calD, calS, places, admin1, new Float(1000), countries, "Gjilan",
				10000);

		journeyRepo.save(journey1);
		journeyRepo.save(journey2);
		journeyRepo.save(journey3);

		return "login";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "error";

	}

}