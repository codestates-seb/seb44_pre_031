import { styled } from 'styled-components';
import { useEffect, useCallback, useState } from 'react';
import { useDaumPostcodePopup } from 'react-daum-postcode';

const MapContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f1f2f3;
  font-size: 0.8rem;
  #map {
    height: 600px;
  }
`;

const InputAddress = styled.div`
  display: flex;
  flex-direction: column;
  margin: 6px 0 6px;

  > div {
    text-align: left;
    margin: 2px 0 2px;
    padding: 0 2px;
    font-size: 1rem;
    font-weight: 800;
  }

  > input {
    margin: 2px 0 2px;
    border: 1px solid #babfc4;
    border-radius: 3px;
    padding: 0.6em 0.7em;
    color: #0c0d0e;
  }
`;
const SettingAddress = () => {
  const [address, setAddress] = useState('');
  const open = useDaumPostcodePopup(
    'https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js'
  );
  // const goEmail =
  const handleComplete = (data) => {
    console.log(data);
    console.log(data.roadAddress); // e.g. '서울 성동구 왕십리로2길 20 (성수동1가)'
    setAddress(data.roadAddress);
    goAddress();
  };
  const handleClick = () => {
    open({ onComplete: handleComplete });
  };
  const new_script = (src) => {
    return new Promise((resolve, reject) => {
      const script = document.createElement('script');
      script.src = src;
      script.addEventListener('load', () => {
        resolve();
      });
      script.addEventListener('error', (e) => {
        reject(e);
      });
      document.head.appendChild(script);
    });
  };
  useEffect(() => {
    const my_script = new_script(
      'https://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=2e9c72e22b8b9402a65bbc568e1d75b1'
    );

    //스크립트 읽기 완료 후 카카오맵 설정
    my_script.then(() => {
      console.log('script loaded!!!');
      const kakao = window['kakao'];
      kakao.maps.load(() => {
        const mapContainer = document.getElementById('map');
        const options = {
          center: new kakao.maps.LatLng(37.56000302825312, 126.97540593203321), //좌표설정
          level: 3,
        };
        const map = new kakao.maps.Map(mapContainer, options); //맵생성
        //마커설정
        const markerPosition = new kakao.maps.LatLng(
          37.56000302825312,
          126.97540593203321
        );
        //초기값 숭례문
        const marker = new kakao.maps.Marker({
          position: markerPosition,
        });
        marker.setMap(map);
      });
    });
  }, []);

  /// mapContainer = container
  //map == map
  const goAddress = useCallback(() => {
    console.log('들어왔다');
    const kakao = window['kakao'];
    kakao.maps.load(() => {
      const mapContainer = document.getElementById('map'); //div이름
      const options = {
        center: new kakao.maps.LatLng(37.56000302825312, 126.97540593203321), //좌표설정
        level: 3,
      };
      const map = new kakao.maps.Map(mapContainer, options); //맵생성
      //마커설정
      const geocoder = new kakao.maps.services.Geocoder();
      // 주소로 좌표를 검색합니다..
      //현재 새주소만 인정되는 문제가 있음
      geocoder.addressSearch(address, function (result, status) {
        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

          // 결과값으로 받은 위치를 마커로 표시합니다
          var marker = new kakao.maps.Marker({
            map: map,
            position: coords,
          });

          // 인포윈도우로 장소에 대한 설명을 표시합니다
          var infowindow = new kakao.maps.InfoWindow({
            content:
              '<div style="width:150px;color:red;text-align:center;padding:6px 0;">내가 썼지롱</div>',
          });
          infowindow.open(map, marker);

          // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
          map.setCenter(coords);
        }
      });
    });
  }, [address]);
  useEffect(() => {
    goAddress();
  }, [goAddress]);

  return (
    <MapContainer>
      <InputAddress>
        <input
          type="address"
          id="address"
          placeholder="서울특별시 중구 세종대로 40"
          value={address}
          readOnly
        ></input>
        <button type="button" onClick={handleClick}>
          Open
        </button>
      </InputAddress>
      <div id="map" className="map/"></div>
    </MapContainer>
  );
};

export default SettingAddress;
